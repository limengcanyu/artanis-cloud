package com.spring.cloud.gateway.gatewayFilter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Spring Framework URI templates 匹配 path 方式限流
 */
@Configuration
public class RequestRateLimiterGatewayFilter2 {
    /**
     * localhost:8780/microservice1/echo/1212
     * localhost:8780/microservice1/echo?user=1212
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routes2(RouteLocatorBuilder builder) {
        return builder.routes().route("requestratelimiter_route", predicateSpec ->
                predicateSpec.path("/microservice1/echo/{user}") // 设置 predicates 的匹配规则，匹配特定的path
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.setPath("/microservice1/echo/{user}") // 设置拦截的path
                                .stripPrefix(1)
                                .requestRateLimiter(
                                        config -> {
                                            config.setKeyResolver(userKeyResolver2());
                                            config.setRateLimiter(redisRateLimiter2());
                                        }
                                )
                        ).uri("lb://spring-cloud-microservice1") // 调用注册中心的 spring-cloud-microservice1 服务实例
        ).build();
    }

    @Bean
    KeyResolver userKeyResolver2() {
//        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("user")));
        return exchange -> Mono.just(Objects.requireNonNullElse(exchange.getRequest().getQueryParams().getFirst("user"), ""));
    }

    @Bean
    RedisRateLimiter redisRateLimiter2() {
        return new RedisRateLimiter(100, 500);
    }

}
