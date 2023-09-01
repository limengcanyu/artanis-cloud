package com.spring.cloud.sleuth.skywalking1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-sleuth-skywalking2")
public interface SkyWalking2Client {
    @RequestMapping("/echoSkywalking2/{content}")
    String echoSkywalking2(@PathVariable String content);
}
