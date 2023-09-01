package com.spring.cloud.sleuth.skywalking1;

import com.spring.cloud.sleuth.skywalking1.feign.SkyWalking2Client;
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
public class SpringCloudSleuthSkywalking1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthSkywalking1Application.class, args);
	}

	@Autowired
	private SkyWalking2Client skyWalking2Client;

	/**
	 * localhost:8800/echoSkywalking1
	 *
	 * @return
	 */
	@RequestMapping("/echoSkywalking1")
	public String echoSkywalking1() {
		return "Skywalking1 return";
	}

	/**
	 * localhost:8800/echoSkywalking1/Skywalking1
	 *
	 * @param content
	 * @return
	 */
	@RequestMapping("/echoSkywalking1/{content}")
	public String echoSkywalking1(@PathVariable String content) {
		log.debug("echoSkywalking1 receive param: {}", content);
		return "Skywalking1 return " + skyWalking2Client.echoSkywalking2(content);
	}

}
