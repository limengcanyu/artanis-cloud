package com.spring.cloud.order.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-cloud-point-service")
public interface PointFeignClient {

    @GetMapping("point/increase")
    Boolean increase(@RequestParam("pointCode") String pointCode, @RequestParam("count") Integer count);

}
