package com.spring.cloud.nacos.microservice3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringCloudNacosMicroservice3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNacosMicroservice3Application.class, args);
    }

    /**
     * localhost:8820/echoService3/content
     *
     * @param content
     * @return
     */
    @RequestMapping("/echoService3/{content}")
    public String echoService3(@PathVariable String content){
        return "service3 return " + content;
    }
}
