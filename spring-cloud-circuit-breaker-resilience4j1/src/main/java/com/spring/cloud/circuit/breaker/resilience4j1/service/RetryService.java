package com.spring.cloud.circuit.breaker.resilience4j1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryCircuitBreakerFactory;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

//    @Retryable(RemoteAccessException.class)
    @Retryable(value = Exception.class, maxAttempts = 5) // 此处配置的重试次数有效
    public void service() throws Exception {
        // ... do something
        System.out.println("====== service"); // 出现异常，重试3次，打印3条信息，然后执行 recover
        throw new Exception();
    }

    @Recover
    public void recover(Exception e) {
        // ... panic
        System.out.println("====== recover");
    }
}
