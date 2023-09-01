package com.spring.cloud.eureka.microservice1.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("spring-cloud-eureka-microservice2")
public interface Service2Client {
    @GetMapping("/echoService2/{param}")
    String echoService2(@PathVariable String param);
}
