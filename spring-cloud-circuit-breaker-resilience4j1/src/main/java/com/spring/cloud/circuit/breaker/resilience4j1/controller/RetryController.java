package com.spring.cloud.circuit.breaker.resilience4j1.controller;

import com.spring.cloud.circuit.breaker.resilience4j1.service.HttpBinService;
import com.spring.cloud.circuit.breaker.resilience4j1.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
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
public class RetryController {

    @Autowired
    private RetryService retryService;

    /**
     * localhost:8800/service
     *
     * @return
     */
    @GetMapping("/service")
    public String get() {
        try {
            retryService.service();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "retry result";
    }

    /**
     * localhost:8800/retry
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = Exception.class, maxAttempts = 5)
    @GetMapping("/retry")
    public String retry() throws Exception {
        System.out.println("====== retry");
        throw new Exception();
    }

    /**
     * Controller 层可以执行 retry，但无法执行 recover
     * @param e
     */
    @Recover
    public void recover(Exception e) {
        // ... panic
        System.out.println("====== recover");
    }
}
