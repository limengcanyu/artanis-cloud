package com.spring.cloud.gateway.gatewayFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

/**
 * <p>Description: Filter Config </p>
 *
 * @author rock.jiang
 * Date 2019/12/24 17:21
 */
@Slf4j
//@Configuration
public class ModifyRequestBodyGatewayFilterFactory {

    /**
     * modifyRequestBody
     *
     * http://localhost:8780/microservice1/modifyRequestBody
     *
     * body:
     * {
     * 	"tenantId": "tenant_001"
     * }
     *
     * output:
     * MicroService1 modifyRequestBody {message=
     * {
     * 	"TENANTID": "TENANT_001"
     * }
     * }
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("rewrite_request_obj", predicateSpec ->
                        predicateSpec.path("/microservice1/**") // 设置 predicates 的匹配模式
                                .filters(gatewayFilterSpec ->
                                        gatewayFilterSpec.setPath("/microservice1/modifyRequestBody") // 设置拦截的指定路径
                                                .stripPrefix(1)
                                                .modifyRequestBody(
                                                        String.class, // inClass
                                                        Hello.class, // outClass
                                                        MediaType.APPLICATION_JSON_VALUE, // newContentType
                                                        (serverWebExchange, s) -> {
                                                            log.debug("=== message: {}", s);
                                                            return Mono.just(new Hello(s.toUpperCase()));
                                                        }
                                                )
                                ).uri("lb://spring-cloud-microservice1") // 调用注册中心的 spring-cloud-microservice1 服务实例
                ).build();
    }

    static class Hello {
        String message;

        public Hello() {
        }

        public Hello(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
