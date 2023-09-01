package com.spring.cloud.circuit.breaker.hystrix2.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class StoreClientFallbackFactory implements FallbackFactory<StoreClient> {

    @Override
    public StoreClient create(Throwable cause) {
        return () -> "getStores fallback; reason was: " + cause.getMessage();
    }
}
