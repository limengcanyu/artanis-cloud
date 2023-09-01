package com.spring.cloud.nacos.microservice1.controller;

import com.spring.cloud.nacos.microservice1.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {
    @Autowired
    private SentinelService sentinelService;

    /**
     * localhost:8800/hello/sentinel
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return sentinelService.sayHello(name);
    }
}
