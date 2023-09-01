//package com.spring.cloud.user.service.controller;
//
//import com.spring.cloud.user.service.service.SentinelService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class SentinelController {
//
//    @Autowired
//    private SentinelService sentinelService;
//
//    /**
//     * <a href="http://localhost:8081/hello/alice">http://localhost:8081/hello/alice</a>
//     *
//     * @return
//     */
//    @GetMapping(value = "/hello/{name}")
//    public String hello(@PathVariable String name) {
//        return sentinelService.sayHello(name);
//    }
//
//}
