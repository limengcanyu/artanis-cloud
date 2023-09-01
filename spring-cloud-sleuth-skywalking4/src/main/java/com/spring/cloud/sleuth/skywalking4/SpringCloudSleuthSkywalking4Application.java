package com.spring.cloud.sleuth.skywalking4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringCloudSleuthSkywalking4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthSkywalking4Application.class, args);
	}

	/**
	 * localhost:8830/echoSkywalking4/Skywalking4
	 *
	 * @param content
	 * @return
	 */
	@RequestMapping("/echoSkywalking4/{content}")
	public String echoSkywalking4(@PathVariable String content){
		log.debug("echoSkywalking4 receive param: {}", content);
		return "skywalking4 return " + content;
	}

}
