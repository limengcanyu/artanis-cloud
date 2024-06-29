package com.spring.cloud.common.circuitbreaker.sentinel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringCloudCommonCircuitBreakerSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCommonCircuitBreakerSentinelApplication.class, args);

    }

}
