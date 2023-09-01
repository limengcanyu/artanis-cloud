package com.spring.cloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GatewayFilterConfig {

//    /**
//     * 编程方式配置路由
//     *
//     * @param builder
//     * @return
//     */
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p ->
//                        p.path("/service1/**")
//                                .filters(f -> f.stripPrefix(1))
//                                .uri("lb://spring-cloud-micro-service1")
//                )
//                .route(p ->
//                        p.path("/service2/**")
//                                .filters(f -> f.stripPrefix(1))
//                                .uri("lb://spring-cloud-micro-service2")
//                )
//                .route(p ->
//                        p.path("/service3/**")
//                                .filters(f -> f.stripPrefix(1))
//                                .uri("lb://spring-cloud-micro-service3")
//                )
//                .build();
//    }

}
