package com.spring.cloud.micro.service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringCloudMicroService2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMicroService2Application.class, args);
    }

}
