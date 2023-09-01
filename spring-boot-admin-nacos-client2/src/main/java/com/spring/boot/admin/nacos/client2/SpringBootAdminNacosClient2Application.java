package com.spring.boot.admin.nacos.client2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootAdminNacosClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminNacosClient2Application.class, args);
	}

	/**
	 * localhost:8772/echo
	 *
	 * @return
	 */
	@RequestMapping("/echo")
	public String echo() {
		log.debug("=== access SpringBootAdminEurekaClient2 echo");
		return "SpringBootAdminEurekaClient2";
	}
}
