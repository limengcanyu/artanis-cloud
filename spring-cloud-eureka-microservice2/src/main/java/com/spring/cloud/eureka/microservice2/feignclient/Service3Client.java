package com.spring.cloud.eureka.microservice2.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("spring-cloud-eureka-microservice3")
public interface Service3Client {
    @GetMapping("/echoService3/{param}")
    String echoService3(@PathVariable String param);
}
