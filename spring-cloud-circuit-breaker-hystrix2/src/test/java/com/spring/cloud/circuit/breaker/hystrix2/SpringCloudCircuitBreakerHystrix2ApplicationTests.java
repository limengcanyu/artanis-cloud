package com.spring.cloud.circuit.breaker.hystrix2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpringCloudCircuitBreakerHystrix2ApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void test() {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 5; i++) {
            String result = restTemplate.getForObject("http://localhost:8300/getStores", String.class);
            System.out.println("第 " + i + " 次调用 result: " + result);
        }

        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

}
