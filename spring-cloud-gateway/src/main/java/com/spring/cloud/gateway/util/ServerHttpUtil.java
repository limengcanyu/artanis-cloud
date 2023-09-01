package com.spring.cloud.gateway.util;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ServerHttpUtil {

    public static Mono<Void> writeResponse(ServerWebExchange exchange, Map<String, String> error) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(error).getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Flux.just(dataBuffer));
    }

}
