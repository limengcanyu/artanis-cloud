package com.spring.cloud.nacos.microservice1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("spring-cloud-nacos-microservice2")
public interface Service2Client {
    @GetMapping("/echoService2/{content}")
    String echoService2(@PathVariable("content") String content);
}
