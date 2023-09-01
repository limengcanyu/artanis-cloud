package com.spring.cloud.circuit.breaker.resilience4j1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudCircuitBreakerResilience4j1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCircuitBreakerResilience4j1Application.class, args);
    }

}
