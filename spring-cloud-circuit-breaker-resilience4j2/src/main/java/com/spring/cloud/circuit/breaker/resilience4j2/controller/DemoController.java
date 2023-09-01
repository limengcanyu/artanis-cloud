package com.spring.cloud.circuit.breaker.resilience4j2.controller;

import com.spring.cloud.circuit.breaker.resilience4j2.feign.Resilience4j1Client;
import com.spring.cloud.circuit.breaker.resilience4j2.service.HttpBinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/23 20:31
 */
@Slf4j
@RestController
public class DemoController {
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private Resilience4j1Client resilience4j1Client;

    /**
     * localhost:8810/get
     *
     * @return
     */
    @Retryable(value = Exception.class, maxAttempts = 3)
    @GetMapping("/get")
    public Map get() {
        return resilience4j1Client.get();
    }

//    /**
//     * localhost:8810/delay/5
//     * <p>
//     * 超时产生断路
//     *
//     * @param seconds
//     * @return
//     */
//    @GetMapping("/delay/{seconds}")
//    public Map delay(@PathVariable int seconds) {
//        return circuitBreakerFactory.create("delay").run(httpBin.delaySuppplier(seconds), t -> {
//            log.warn("delay call failed error", t);
//            Map<String, String> fallback = new HashMap<>();
//            fallback.put("hello", "world");
//            return fallback; // 返回断路回调结果
//        });
//    }
}
