package com.spring.cloud.sleuth.zipkin3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringCloudSleuthZipkin3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthZipkin3Application.class, args);
	}

	/**
	 * localhost:8880/echoZipkin3/zipkin3
	 *
	 * @param content
	 * @return
	 */
	@RequestMapping("/echoZipkin3/{content}")
	public String echoZipkin3(@PathVariable String content){
		return "zipkin3 return " + content;
	}

}
