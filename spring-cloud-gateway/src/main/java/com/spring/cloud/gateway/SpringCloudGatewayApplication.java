package com.spring.cloud.gateway;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) throws URISyntaxException {
        URI uri1 = new URI("micro-service1");
        URI uri2 = new URI("micro-service2");
        return builder.routes()
                .route("micro-service1",
                        r -> r.path("/micro-service1/**")
                                .filters(f -> f.stripPrefix(1).cacheRequestBody(JSONObject.class))
                                .uri(uri1)
                )
                .route("micro-service2",
                        r -> r.path("/micro-service2/**")
                                .filters(f -> f.stripPrefix(1).cacheRequestBody(JSONObject.class))
                                .uri(uri2)
                )
                .build();
    }

}
