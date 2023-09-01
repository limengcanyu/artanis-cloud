package com.spring.cloud.micro.service1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("spring-cloud-micro-service2")
public interface StoreClient {

    @PostMapping("/getStores")
    String getStores(@RequestBody Map<String, Object> map);

}
