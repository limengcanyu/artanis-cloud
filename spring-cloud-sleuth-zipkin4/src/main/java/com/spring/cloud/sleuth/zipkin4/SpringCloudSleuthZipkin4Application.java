package com.spring.cloud.sleuth.zipkin4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringCloudSleuthZipkin4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthZipkin4Application.class, args);
	}

	/**
	 * localhost:8880/echoZipkin4/zipkin4
	 *
	 * @param content
	 * @return
	 */
	@RequestMapping("/echoZipkin4/{content}")
	public String echoZipkin4(@PathVariable String content){
		return "zipkin4 return " + content;
	}

}
