package com.spring.cloud.circuit.breaker.resilience4j1.controller;

import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.internal.SemaphoreBulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/9/13 15:05
 */
@Slf4j
@RestController
public class BulkheadController {

    @Autowired
    private ThreadPoolBulkhead threadPoolBulkhead;

    @Autowired
    private SemaphoreBulkhead semaphoreBulkhead;

    /**
     * localhost:8800/decorateRunnable
     *
     * @return
     */
    @RequestMapping("/decorateRunnable")
    public String decorateRunnable() {
        threadPoolBulkhead.decorateRunnable(() -> System.out.println("decorateRunnable"));
        return "decorateRunnable";
    }

    /**
     * localhost:8800/executeRunnable
     *
     * @return
     */
    @RequestMapping("/executeRunnable")
    public String executeRunnable() {
        if (semaphoreBulkhead.tryAcquirePermission()) {
            semaphoreBulkhead.acquirePermission();

            semaphoreBulkhead.executeRunnable(() -> System.out.println("executeRunnable"));

            semaphoreBulkhead.onComplete();

            semaphoreBulkhead.releasePermission();
        } else {
            System.out.println("没信号了，不能调用");
        }
        return "executeRunnable";
    }
}
