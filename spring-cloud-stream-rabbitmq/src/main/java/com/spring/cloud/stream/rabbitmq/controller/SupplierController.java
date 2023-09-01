package com.spring.cloud.stream.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {

    @Autowired
    private StreamBridge streamBridge;

    /**
     * localhost:8080/inputCase
     *
     * streamBridge 将消息发送到 inputCase-in-0，然后依次经过 uppercase;lowerCase;stringConsumer
     *
     * @return
     */
    @RequestMapping("/inputCase")
    public String inputCase() {
        streamBridge.send("inputCase-in-0", "sdfsdfsdf");
        return "success";
    }

}
