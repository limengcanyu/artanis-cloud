package com.spring.cloud.circuit.breaker.resilience4j2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient("spring-cloud-circuit-breaker-resilience4j1")
public interface Resilience4j1Client {

    @GetMapping("/get")
    Map get();
}
