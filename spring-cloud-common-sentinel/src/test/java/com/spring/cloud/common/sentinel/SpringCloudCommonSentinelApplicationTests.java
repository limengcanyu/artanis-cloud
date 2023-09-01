package com.spring.cloud.common.sentinel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpringCloudCommonSentinelApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	void contextLoads() {
		String url = "http://localhost:8080/hello/rock";
		restTemplate.getForObject(url, String.class);
	}

}
