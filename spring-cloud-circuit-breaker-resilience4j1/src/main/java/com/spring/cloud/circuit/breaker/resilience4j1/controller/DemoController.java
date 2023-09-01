package com.spring.cloud.circuit.breaker.resilience4j1.controller;

import com.spring.cloud.circuit.breaker.resilience4j1.service.HttpBinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.retry.support.RetryTemplate;
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
    private CircuitBreakerFactory circuitBreakerFactory;
    private HttpBinService httpBin;
    private RetryTemplate retryTemplate;

    public DemoController(CircuitBreakerFactory circuitBreakerFactory, HttpBinService httpBinService, RetryTemplate retryTemplate) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.httpBin = httpBinService;
        this.retryTemplate = retryTemplate;
    }

    /**
     * localhost:8800/get
     *
     * @return
     */
    @GetMapping("/get")
    public Map get() {
        return httpBin.get();
    }

    /**
     * localhost:8800/delay/5
     * <p>
     * 超时产生断路
     *
     * @param seconds
     * @return
     */
    @GetMapping("/delay/{seconds}")
    public Map delay(@PathVariable int seconds) {
        // CircuitBreaker Id: delay
        return circuitBreakerFactory.create("delay").run(httpBin.delaySupplier(seconds), t -> {
            log.warn("delay call failed error", t);

            Map<String, String> fallback = new HashMap<>();
            fallback.put("fallback", "this is fallback result");
            return fallback; // 返回断路回调结果
        });
    }
}
