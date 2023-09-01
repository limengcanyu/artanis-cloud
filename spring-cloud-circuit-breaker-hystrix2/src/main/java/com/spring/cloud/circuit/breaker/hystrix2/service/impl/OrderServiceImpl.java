package com.spring.cloud.circuit.breaker.hystrix2.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.cloud.circuit.breaker.hystrix2.feign.StoreClient;
import com.spring.cloud.circuit.breaker.hystrix2.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/04/19 19:41
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StoreClient storeClient;

    @HystrixCommand(fallbackMethod = "defaultOrders")
    @Override
    public String getOrders(Map<String, Object> parameters) throws Exception {
        //do stuff that might fail
//        return "normal return";
        throw new RuntimeException(); // 出现异常调用 fallbackMethod 返回
    }

    public String defaultOrders(Map<String, Object> parameters) {
        return "getOrders fallback return";
    }

    /**
     * 熔断封装Feign，Feign封装Ribbon
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "defaultStores", commandProperties = {
            // 编码方式指定熔断属性
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @Override
    public String getStores() {
        return storeClient.getStores();
    }

    public String defaultStores() {
        log.debug("====== getStores 调用超时，发生熔断");
        return "defaultStores fallback return";
    }

}
