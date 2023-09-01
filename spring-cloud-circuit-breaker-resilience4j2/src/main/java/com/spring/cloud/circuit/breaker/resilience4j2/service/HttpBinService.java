package com.spring.cloud.circuit.breaker.resilience4j2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.function.Supplier;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/23 20:30
 */
@Service
public class HttpBinService {
    private RestTemplate rest;

    public HttpBinService(RestTemplate rest) {
        this.rest = rest;
    }

    public Map get() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rest.getForObject("https://httpbin.org/get", Map.class);

    }

    public Map delay(int seconds) {
        return rest.getForObject("https://httpbin.org/delay/" + seconds, Map.class);
    }

    public Supplier<Map> delaySuppplier(int seconds) {
        return () -> this.delay(seconds);
    }
}
