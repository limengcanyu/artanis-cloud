package com.spring.cloud.gateway.gatewayFilter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class AddRequestHeaderGatewayFilterFactory {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("add_request_header", predicateSpec ->
                        predicateSpec.path("/microservice1/**") // 设置 predicates 的匹配模式
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec.setPath("/microservice1/modifyRequestBody") // 设置拦截的指定路径
                                                .stripPrefix(1)
                                                .addRequestHeader("tenant_id", "tenant_000000")
                                ).uri("lb://spring-cloud-microservice1") // 调用注册中心的 spring-cloud-microservice1 服务实例
                ).build();
    }

}
