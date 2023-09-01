package com.spring.cloud.eureka.microservice2;

import com.spring.cloud.eureka.microservice2.feignclient.Service3Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableFeignClients
@SpringBootApplication
public class SpringCloudEurekaMicroservice2Application {

    @Autowired
    private Service3Client service3Client;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaMicroservice2Application.class, args);
    }

    /**
     * localhost:8840/echoService2/service2
     *
     * @param param
     * @return
     */
    @RequestMapping("/echoService2/{param}")
    public String echoService2(@PathVariable String param) {
        log.debug("call echoService2 ====================================");
        return "echo service2 param: " + service3Client.echoService3(param);
    }

}
