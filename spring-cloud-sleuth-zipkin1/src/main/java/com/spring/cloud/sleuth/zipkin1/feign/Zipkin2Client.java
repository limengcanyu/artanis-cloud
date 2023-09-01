package com.spring.cloud.sleuth.zipkin1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-sleuth-zipkin2")
public interface Zipkin2Client {
    @RequestMapping("/echoZipkin2/{content}")
    String echoZipkin2(@PathVariable String content);
}
