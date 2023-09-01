package com.spring.cloud.circuit.breaker.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class SpringCloudCircuitBreakerHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCircuitBreakerHystrixDashboardApplication.class, args);
    }

}
