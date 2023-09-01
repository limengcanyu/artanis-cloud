package com.spring.boot.admin.eureka.client2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class SpringBootAdminEurekaClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminEurekaClient2Application.class, args);
	}

	/**
	 * localhost:8772/echo
	 *
	 * @return
	 */
	@RequestMapping("/echo")
	public String echo() {
		return "SpringBootAdminEurekaClient1";
	}
}
