package com.spring.cloud.sleuth.zipkin2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-sleuth-zipkin4")
public interface Zipkin4Client {
    @RequestMapping("/echoZipkin4/{content}")
    String echoZipkin4(@PathVariable String content);
}
