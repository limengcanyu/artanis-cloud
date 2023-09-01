package com.spring.cloud.circuit.breaker.resilience4j2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class SpringCloudCircuitBreakerResilience4j2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCircuitBreakerResilience4j2Application.class, args);
    }

}
