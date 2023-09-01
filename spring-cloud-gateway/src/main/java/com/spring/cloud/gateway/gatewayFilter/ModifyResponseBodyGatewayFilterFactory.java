package com.spring.cloud.gateway.gatewayFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@Slf4j
//@Configuration
public class ModifyResponseBodyGatewayFilterFactory {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("rewrite_response_upper",
                        predicateSpec -> predicateSpec.path("/microservice1/**")
                                .filters(
                                        gatewayFilterSpec ->
                                                gatewayFilterSpec.prefixPath("/httpbin")
                                                        .modifyResponseBody(
                                                                String.class,
                                                                String.class,
                                                                (serverWebExchange, s) -> Mono.just(s.toUpperCase())
                                                        )
                                )
                                .uri("lb://spring-cloud-microservice1")
                ).build();

    }
}
