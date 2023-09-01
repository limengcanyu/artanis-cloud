package com.spring.cloud.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Objects;
import java.util.TreeMap;

/**
 *
 */
@Slf4j
//@Component
public class ModifyRequestGlobalFilter implements GlobalFilter, Ordered {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("request global filter");

        exchange.getAttributes().put("startTime", System.currentTimeMillis());

        HttpMethod httpMethod = exchange.getRequest().getMethod();
        if (HttpMethod.POST.equals(httpMethod)) {
            Class<String> inClass = String.class;
            Class<String> outClass = String.class;
            ServerRequest serverRequest = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());
            MediaType mediaType = exchange.getRequest().getHeaders().getContentType();

            String token = exchange.getRequest().getHeaders().getFirst("token");
            String sign = exchange.getRequest().getHeaders().getFirst("sign");
            log.info("token: {} sign: {}", token, sign);

            // TODO: flux or mono
            Mono<String> modifiedBody = serverRequest.bodyToMono(inClass).flatMap(originalBody -> {
                if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
                    log.info("json body: {}", originalBody);

                    try {
                        TreeMap paramMap = objectMapper.readValue(originalBody, TreeMap.class);

                        boolean verifySignature = verifySignature(paramMap);
                        if (!verifySignature) {
                            return Mono.error(new Exception("签名验证不通过！"));
                        }

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
        } else {
            MultiValueMap<String, String> map = exchange.getRequest().getQueryParams();
            if (!CollectionUtils.isEmpty(map)) {
                TreeMap<String, String> paramMap = new TreeMap<>();
                map.forEach((key, value) -> paramMap.put(key, String.valueOf(value)));

                boolean verifySignature = verifySignature(paramMap);
                if (!verifySignature) {
                    return Mono.error(new Exception("签名验证不通过！"));
                }

            }
            return returnMono(chain, exchange);
        }
    }

    ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    private boolean verifySignature(TreeMap paramMap) {
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
