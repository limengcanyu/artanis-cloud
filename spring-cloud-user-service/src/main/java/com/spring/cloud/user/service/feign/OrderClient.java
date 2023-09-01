package com.spring.cloud.user.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("spring-cloud-order-service")
//@FeignClient(name = "spring-cloud-order-service", url = "http://localhost:8082") // 如果指定url，则使用url指定的地址访问
public interface OrderClient {
    @PostMapping("/getUserOrders")
    String getUserOrders(@RequestBody Map<String, Object> map);

}
