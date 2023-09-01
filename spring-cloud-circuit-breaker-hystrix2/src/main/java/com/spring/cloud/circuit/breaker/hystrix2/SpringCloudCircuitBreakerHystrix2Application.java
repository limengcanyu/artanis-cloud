package com.spring.cloud.circuit.breaker.hystrix2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudCircuitBreakerHystrix2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCircuitBreakerHystrix2Application.class, args);
    }

}
