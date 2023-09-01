package com.spring.boot.admin.nacos.client1;

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
public class SpringBootAdminNacosClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminNacosClient1Application.class, args);
	}

	/**
	 * localhost:8771/echo
	 *
	 * @return
	 */
	@RequestMapping("/echo")
	public String echo() {
		log.debug("=== access SpringBootAdminEurekaClient1 echo");
		return "SpringBootAdminEurekaClient1";
	}
}
