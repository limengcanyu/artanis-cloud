package com.spring.cloud.user.service.controller;

import com.spring.cloud.user.service.feign.OrderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RefreshScope
@RestController
public class UserController {
    @Autowired
    private OrderClient orderClient;

    /**
     * http://localhost:8083/getUserInfo
     *
     * @return
     */
    @RequestMapping("/getUserInfo")
    public String getUserInfo() {
        return "user info";
    }

    /**
     * http://localhost:8083/getUserOrders
     *
     * @return
     */
    @GetMapping("/getUserOrders")
    public String getUserOrders(){
        log.debug("====== call getUserOrders");

        orderClient.getUserOrders(new HashMap<>());

        return "ok";
    }

    /**
     * http://localhost:8083/testError
     *
     * @return
     */
    @GetMapping("/testError")
    public String testError(){
        log.debug("====== call error");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (System.currentTimeMillis() % 3 == 0) {
            int a = 1 / 0;
        }

        return "ok";
    }

}
