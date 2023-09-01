package com.spring.cloud.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class SpringCloudAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAdminServerApplication.class, args);
	}

}
