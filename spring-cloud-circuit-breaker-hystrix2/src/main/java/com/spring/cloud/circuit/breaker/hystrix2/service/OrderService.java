package com.spring.cloud.circuit.breaker.hystrix2.service;

import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/04/19 19:41
 */
public interface OrderService {
    String getOrders(Map<String, Object> parameters) throws Exception;

    String getStores();
}
