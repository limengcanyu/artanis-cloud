package com.spring.cloud.sleuth.skywalking2;

import com.spring.cloud.sleuth.skywalking2.feign.SkyWalking3Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableFeignClients
@SpringBootApplication
public class SpringCloudSleuthSkywalking2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthSkywalking2Application.class, args);
	}

	@Autowired
	private SkyWalking3Client skyWalking3Client;

	/**
	 * localhost:8810/echoSkywalking2/Skywalking2
	 *
	 * @param content
	 * @return
	 */
	@RequestMapping("/echoSkywalking2/{content}")
	public String echoSkywalking2(@PathVariable String content) {
		log.debug("echoSkywalking2 receive param: {}", content);
		return "Skywalking2 return " + skyWalking3Client.echoSkywalking3(content);
	}

}
