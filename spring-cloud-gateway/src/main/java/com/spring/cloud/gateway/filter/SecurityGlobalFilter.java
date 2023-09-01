package com.spring.cloud.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.common.constant.RequestConst;
import com.spring.cloud.common.constant.TokenConst;
import com.spring.cloud.common.util.JwtRsaUtil;
import com.spring.cloud.gateway.util.ServerHttpUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
//@Component
public class SecurityGlobalFilter implements GlobalFilter, Ordered {
//    @Autowired
//    private RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(RequestConst.TOKEN);

        String verifySign = exchange.getRequest().getHeaders().getFirst(RequestConst.VERIFY_SIGN);
        log.debug("verifySign: {} token: {}", verifySign, token);

        // 解析token，成功后将token中信息设置在header中，后台微服务通过拦截器获取header中信息
        Claims claims = JwtRsaUtil.parseToken(token);
        log.debug("claims: {}", JSON.toJSONString(claims));
        if (CollectionUtils.isEmpty(claims)) {
            Map<String, String> error = new HashMap<>();
            error.put("code", "1");
            error.put("message", "非法请求！");
            return ServerHttpUtil.writeResponse(exchange, error);
        }

        String tenantId = claims.get(TokenConst.TENANT_ID, String.class);
        String companyId = claims.get(TokenConst.COMPANY_ID, String.class);
        String userId = claims.get(TokenConst.USER_ID, String.class);
        log.debug("tenantId: {} companyId: {} userId: {}", tenantId, companyId, userId);

        AtomicReference<ServerHttpRequest> request = new AtomicReference<>(exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.add(TokenConst.TENANT_ID, tenantId);
            httpHeaders.add(TokenConst.COMPANY_ID, companyId);
            httpHeaders.add(TokenConst.USER_ID, userId);
        }).build());

        // 需要验证签名时返回
        if (StringUtils.hasLength(verifySign) && "true".equals(verifySign)) {
            String sign = exchange.getRequest().getHeaders().getFirst("sign");
            if (!StringUtils.hasLength(sign)) {
                Map<String, String> error = new HashMap<>();
                error.put("code", "1");
                error.put("message", "非法签名！");
                return ServerHttpUtil.writeResponse(exchange, error);
            }

            exchange.getAttributes().put("startTime", System.currentTimeMillis());

            HttpMethod httpMethod = exchange.getRequest().getMethod();
            if (HttpMethod.POST.equals(httpMethod)) {
                return processPost(exchange, chain, tenantId, companyId, userId, request, sign);
            } else {
                return processGet(exchange, chain, tenantId, companyId, userId, request, sign);
            }
        }

        // 不需要验证签名时返回
        return chain.filter(exchange.mutate().request(request.get()).build());
    }

    private Mono<Void> processGet(ServerWebExchange exchange, GatewayFilterChain chain, String tenantId, String companyId, String userId, AtomicReference<ServerHttpRequest> request, String sign) {
        MultiValueMap<String, String> map = exchange.getRequest().getQueryParams();
        if (!CollectionUtils.isEmpty(map)) {
            TreeMap<String, String> paramMap = new TreeMap<>();
            map.forEach((key, value) -> paramMap.put(key, String.valueOf(value)));

            if (!verifySignature(sign, paramMap)) {
                // 签名验证不通过，在header中设置标识
                request.set(exchange.getRequest().mutate().headers(httpHeaders -> {
                    httpHeaders.add(TokenConst.VERIFY_SIGNATURE, TokenConst.VERIFY_SIGNATURE_FAILED);
                }).build());

                return Mono.empty();
            }

            // 签名验证通过，在header中设置用户信息
            request.set(exchange.getRequest().mutate().headers(httpHeaders -> {
                httpHeaders.add(TokenConst.TENANT_ID, tenantId);
                httpHeaders.add(TokenConst.COMPANY_ID, companyId);
                httpHeaders.add(TokenConst.USER_ID, userId);
                httpHeaders.add(TokenConst.VERIFY_SIGNATURE, TokenConst.VERIFY_SIGNATURE_SUCCESS);
            }).build());

        }
        return returnMono(chain, exchange);
    }

    private Mono<Void> processPost(ServerWebExchange exchange, GatewayFilterChain chain, String tenantId, String companyId, String userId, AtomicReference<ServerHttpRequest> request, String sign) {
        Class<String> inClass = String.class;
        Class<String> outClass = String.class;
        ServerRequest serverRequest = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());
        MediaType mediaType = exchange.getRequest().getHeaders().getContentType();

        Mono<String> modifiedBody = serverRequest.bodyToMono(inClass).flatMap(originalBody -> {
            if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
                log.info("json body: {}", originalBody);

                try {
                    TreeMap paramMap = objectMapper.readValue(originalBody, TreeMap.class);

                    if (!verifySignature(sign, paramMap)) {
                        // 签名验证不通过，在header中设置标识
                        request.set(exchange.getRequest().mutate().headers(httpHeaders -> {
                            httpHeaders.add(TokenConst.VERIFY_SIGNATURE, TokenConst.VERIFY_SIGNATURE_FAILED);
                        }).build());

                        return Mono.empty();
                    }

                    // 签名验证通过，在header中设置用户信息
                    request.set(exchange.getRequest().mutate().headers(httpHeaders -> {
                        httpHeaders.add(TokenConst.TENANT_ID, tenantId);
                        httpHeaders.add(TokenConst.COMPANY_ID, companyId);
                        httpHeaders.add(TokenConst.USER_ID, userId);
                        httpHeaders.add(TokenConst.VERIFY_SIGNATURE, TokenConst.VERIFY_SIGNATURE_SUCCESS);
                    }).build());

                    return Mono.just(originalBody);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            if (!StringUtils.hasLength(originalBody)) {
                return Mono.empty();
            }

            return Mono.just(originalBody);
        });

        BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromPublisher(modifiedBody, outClass);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());

        // the new content type will be computed by bodyInserter
        // and then set in the request decorator
        headers.remove(HttpHeaders.CONTENT_LENGTH);

        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            ServerHttpRequest decorator = decorate(exchange, headers, outputMessage);
            return returnMono(chain, exchange.mutate().request(decorator).build());
        }));
    }

    ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    private boolean verifySignature(String sign, TreeMap paramMap) {
        log.debug("verifySignature param： {}", paramMap);
        return false;
    }

    private Mono<Void> returnMono(GatewayFilterChain chain, ServerWebExchange exchange) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute("startTime");

            if (startTime != null) {
                long executeTime = (System.currentTimeMillis() - startTime);
                log.info("cost time：{}ms", executeTime);
                log.info("status code：{}", Objects.requireNonNull(exchange.getResponse().getStatusCode()).value());
            }
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
