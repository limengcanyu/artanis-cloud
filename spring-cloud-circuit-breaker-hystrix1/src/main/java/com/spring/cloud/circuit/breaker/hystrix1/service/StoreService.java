package com.spring.cloud.circuit.breaker.hystrix1.service;

import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/04/19 19:41
 */
public interface StoreService {
    String getStores(Map<String, Object> parameters) throws Exception;
}
