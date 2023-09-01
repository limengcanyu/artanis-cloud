package com.spring.cloud.gateway.gatewayFilter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class SpringCloudCircuitBreakerGatewayFilterFactory {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("circuitbreaker_route", predicateSpec ->
                predicateSpec.path("/microservice1/**") // 设置 predicates 的匹配模式
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.setPath("/microservice1/hystrix_route") // 设置拦截的指定路径
                                .stripPrefix(1)
                                .circuitBreaker(config -> config.setName("myCircuitBreaker").setFallbackUri("forward:/inCaseOfFailureUseThis")) // 发生断路时，请求转发到该配置设置的uri
                        ).uri("lb://spring-cloud-microservice1") // 调用注册中心的 spring-cloud-microservice1 服务实例
        ).build();
    }

}
