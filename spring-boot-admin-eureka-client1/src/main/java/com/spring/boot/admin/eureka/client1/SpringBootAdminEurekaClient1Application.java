package com.spring.boot.admin.eureka.client1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableEurekaClient
@SpringBootApplication
public class SpringBootAdminEurekaClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminEurekaClient1Application.class, args);
	}

	/**
	 * localhost:8771/echo
	 *
	 * @return
	 */
	@RequestMapping("/echo")
	public String echo() {
		log.debug("=== access echo");
		return "SpringBootAdminEurekaClient1";
	}
}
