package com.spring.cloud.gateway.gatewayFilter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 请求参数方式匹配 path 限流
 */
//@Configuration
public class RequestRateLimiterGatewayFilter {
    /**
     * localhost:8780/microservice1/echoParam?user=1212
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routes1(RouteLocatorBuilder builder) {
        return builder.routes().route("requestratelimiter_route", predicateSpec ->
                predicateSpec.path("/microservice1/echoParam") // 设置 predicates 的匹配规则，匹配特定的path
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.setPath("/microservice1/echoParam") // 设置拦截的path
                                .stripPrefix(1)
                                .requestRateLimiter(
                                        config -> {
                                            config.setKeyResolver(userKeyResolver1());
                                            config.setRateLimiter(redisRateLimiter1());
                                        }
                                )
                        ).uri("lb://spring-cloud-microservice1") // 调用注册中心的 spring-cloud-microservice1 服务实例
        ).build();
    }

    /**
     * 请求参数中必须存在user参数
     *
     * @return
     */
    @Primary // 存在多个 KeyResolver Bean 时设置
    @Bean
    KeyResolver userKeyResolver1() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("user")));
    }

    @Primary // 存在多个 RedisRateLimiter Bean 时设置
    @Bean
    RedisRateLimiter redisRateLimiter1() {
        return new RedisRateLimiter(100, 500);
    }

}
