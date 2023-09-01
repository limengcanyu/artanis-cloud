package com.spring.cloud.sleuth.skywalking2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-sleuth-skywalking3")
public interface SkyWalking3Client {
    @RequestMapping("/echoSkywalking3/{content}")
    String echoSkywalking3(@PathVariable String content);
}
