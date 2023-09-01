package com.eureka.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EurekaClient1Application {

    @RequestMapping("/")
    public String home() {
        return "Hello client1";
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient1Application.class, args);
    }
}
