package com.spring.cloud.soul.microservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudSoulMicroservice1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSoulMicroservice1Application.class, args);
    }

}
