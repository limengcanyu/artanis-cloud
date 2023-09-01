package com.spring.cloud.gateway.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>Description: Auth Global Filter </p>
 *
 * @author rock.jiang
 * Date 2019/12/21 17:59
 */
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("Auth Global Filter ====================================== ");
        log.debug("Auth Global Filter 当前线程ID: {} 线程名称: {}", Thread.currentThread().getId(), Thread.currentThread().getName());

        ServerHttpRequest request = exchange.getRequest();
        log.debug("Auth Global Filter 请求对象类型: {}", request.getClass());

        Map<String, Object> requestAttributes = exchange.getAttributes();
        log.debug("Auth Global Filter 请求属性: {}", requestAttributes);

        // 从请求获取token
        String token = request.getHeaders().getFirst("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst("token");
        }
        if (StringUtils.isEmpty(token)) {
            log.debug("Auth Global Filter 请求中token为空！");

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        log.debug("Auth Global Filter 当前线程ID: {} 线程名称: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private void getRequestBody(ServerHttpRequest request) {
        // 获取请求Body
        Flux<DataBuffer> bodyFlux = request.getBody();
        bodyFlux.subscribe(dataBuffer -> {
            log.debug("Auth Global Filter 当前线程ID: {} 线程名称: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
            log.debug("Auth Global Filter dataBuffer: {}", dataBuffer);

            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            DataBufferUtils.release(dataBuffer);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            log.debug("Auth Global Filter bodyString: {}", bodyString);
        });
    }

}
