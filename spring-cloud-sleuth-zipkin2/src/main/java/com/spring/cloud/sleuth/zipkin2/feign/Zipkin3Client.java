package com.spring.cloud.sleuth.zipkin2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-sleuth-zipkin3")
public interface Zipkin3Client {
    @RequestMapping("/echoZipkin3/{content}")
    String echoZipkin3(@PathVariable String content);
}
