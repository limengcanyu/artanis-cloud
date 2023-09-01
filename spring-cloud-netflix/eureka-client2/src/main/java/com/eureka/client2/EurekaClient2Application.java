package com.eureka.client2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EurekaClient2Application {

    @RequestMapping("/")
    public String home() {
        return "Hello client2";
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient2Application.class, args);
    }
}
