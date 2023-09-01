//package com.spring.cloud.gateway.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * <p>Description: Route Config </p>
// *
// * @author rock.jiang
// * Date 2019/12/23 17:55
// */
//@Configuration
//public class RouteConfig {
//
//    /**
//     * 编程方式配置静态路由
//     *
//     * @param builder
//     * @return
//     */
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("security_route", r -> r.path("/security/**").filters(f -> f.stripPrefix(1)).uri("lb://spring-cloud-security"))
//
//                // 添加响应头，可在此添加响应token
//                .route("security_route", r -> r.path("/security/**")
//                        .filters(f -> f.stripPrefix(1).addResponseHeader("X-AnotherHeader", "baz"))
//                        .uri("lb://spring-cloud-security")
//                )
//
//                .route("microservice1_route", r -> r.path("/microservice1/**").filters(f -> f.stripPrefix(1)).uri("lb://spring-cloud-microservice1"))
//                // 添加令牌桶算法限流
//                .route("microservice1_route", r -> r.order(-1)
//                        .path("/microservice1/**")
//                        .filters(f -> f.stripPrefix(1).filter(
//                                new ThrottleGatewayFilter().setCapacity(1).setRefillTokens(1).setRefillPeriod(10).setRefillUnit(TimeUnit.SECONDS)
//                        ))
//                        .uri("lb://spring-cloud-microservice1")
//                        .metadata("key", "value")
//                )
//
//                .route("microservice2_route", r -> r.path("/microservice2/**").filters(f -> {
//                    f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()));
//                    return f.stripPrefix(1);
//                }).uri("lb://spring-cloud-microservice2"))
//                .build();
//    }
//
//    @Bean
//    RedisRateLimiter redisRateLimiter() {
//        return new RedisRateLimiter(1, 2);
//    }
//}
