package com.spring.cloud.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.common.constant.RequestConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class SecurityGlobalFilter implements GlobalFilter, Ordered {
//    @Autowired
//    private RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String token = request.getHeaders().getFirst(RequestConst.TOKEN);

        RequestPath path = request.getPath();

        // Get请求参数
        MultiValueMap<String, String> queryParams = request.getQueryParams();

        // Post请求参数
        Object body = exchange.getAttribute(ServerWebExchangeUtils.CACHED_REQUEST_BODY_ATTR);

        Map<String, Object> attributes = exchange.getAttributes();
        attributes.put("startTime", System.currentTimeMillis());

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
        return LOWEST_PRECEDENCE; // 优先级最低，等执行完缓存Body之后再执行本过滤器
    }

}
