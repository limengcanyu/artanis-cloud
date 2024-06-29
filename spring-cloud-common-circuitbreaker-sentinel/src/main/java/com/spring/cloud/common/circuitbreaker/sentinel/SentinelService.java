package com.spring.cloud.common.circuitbreaker.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SentinelService {

    /**
     * 抛出 BlockException，ControllerAdvice中直接使用
     *
     * @param name
     * @return
     * @throws BlockException
     */
    @SentinelResource(value = "sayHello")
    public String sayHello(String name) throws BlockException {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Hello, " + name;
    }

}
