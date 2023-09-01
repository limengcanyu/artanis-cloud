package com.spring.cloud.micro.service3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringCloudMicroService3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMicroService3Application.class, args);
    }

}
