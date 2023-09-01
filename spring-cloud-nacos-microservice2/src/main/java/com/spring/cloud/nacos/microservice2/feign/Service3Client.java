package com.spring.cloud.nacos.microservice2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("spring-cloud-nacos-microservice3")
public interface Service3Client {
    @GetMapping("/echoService3/{content}")
    String echoService3(@PathVariable String content);
}
