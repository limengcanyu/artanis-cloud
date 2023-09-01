package com.spring.cloud.eureka.microservice3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringCloudEurekaMicroservice3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaMicroservice3Application.class, args);
    }

    /**
     * localhost:8850/echoService3/service3
     *
     * @param param
     * @return
     */
    @RequestMapping("/echoService3/{param}")
    public String echoService3(@PathVariable String param) {
        log.debug("call echoService3 ====================================");
        return "echo service3 param: " + param;
    }

}
