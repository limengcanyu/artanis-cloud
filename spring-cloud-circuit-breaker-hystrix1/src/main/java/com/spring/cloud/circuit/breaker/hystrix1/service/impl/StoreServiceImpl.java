package com.spring.cloud.circuit.breaker.hystrix1.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.cloud.circuit.breaker.hystrix1.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/04/19 19:41
 */
@Slf4j
@Service
public class StoreServiceImpl implements StoreService {

//    @HystrixCommand(fallbackMethod = "defaultStores" )
    @Override
    public String getStores(Map<String, Object> parameters) throws Exception {
        log.debug("====== getStores");
        //do stuff that might fail
        // 正常返回
//        return "normal return";

        // 出现异常调用 fallbackMethod 返回
//        throw new RuntimeException();

        TimeUnit.SECONDS.sleep(3);
        return "normal return";
    }

    public String defaultStores(Map<String, Object> parameters) {
        return "fallback return";
    }
}
