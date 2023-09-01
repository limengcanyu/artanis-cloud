package com.spring.cloud.circuit.breaker.hystrix2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * To enable fallbacks for a given @FeignClient set the fallback attribute to the class name that implements the fallback.
 * You also need to declare your implementation as a Spring bean.
 *
 * If one needs access to the cause that made the fallback trigger,
 * one can use the fallbackFactory attribute inside @FeignClient.
 */
@Primary
@FeignClient(
        name = "spring-cloud-circuit-breaker-hystrix1",
        fallback = StoreClientFallback.class,
        fallbackFactory = StoreClientFallbackFactory.class
)
public interface StoreClient {
    @RequestMapping("/getStores")
    String getStores();
}
