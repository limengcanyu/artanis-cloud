package com.spring.cloud.circuit.breaker.hystrix2.feign;

import org.springframework.stereotype.Component;

@Component
public class StoreClientFallback implements StoreClient {
    @Override
    public String getStores() {
        return "getStores feign fallback";
    }
}
