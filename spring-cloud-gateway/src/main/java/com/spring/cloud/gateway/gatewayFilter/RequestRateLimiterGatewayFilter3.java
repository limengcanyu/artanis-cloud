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
 *
 */
//@Configuration
public class RequestRateLimiterGatewayFilter3 {
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
                // predicateSpec 指定 path 为 /microservice1/**，而 gatewayFilterSpec 指定 path 为 /microservice1/echoParam 时，
                // 匹配谓词的 path 都符合条件，但是不匹配 gatewayFilterSpec 的 path 会因为没有 KeyResolver 中指定的 key 而调用失败
                // 解决办法：谓词 path 和 过滤器 path 一一对应，不用范围限定
                predicateSpec.path("/microservice1/**") // 设置 predicates 的匹配规则，匹配特定的path
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.setPath("/microservice1/echoParam") // 设置拦截的path
                                .stripPrefix(1)
                                .requestRateLimiter(
                                        config -> {
                                            config.setKeyResolver(userKeyResolver3());
                                            config.setRateLimiter(redisRateLimiter3());
                                        }
                                )
                        ).uri("lb://spring-cloud-microservice1") // 调用注册中心的 spring-cloud-microservice1 服务实例
        ).build();
    }

    /**
     * 使用 Objects.requireNonNullElse 提示信息更加友好
     *
     * @return
     */
    @Bean
    KeyResolver userKeyResolver3() {
        return exchange -> Mono.just(Objects.requireNonNullElse(exchange.getRequest().getQueryParams().getFirst("user"), ""));
    }

    @Bean
    RedisRateLimiter redisRateLimiter3() {
        return new RedisRateLimiter(100, 500);
    }

}
