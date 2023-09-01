package com.spring.cloud.order.service;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.TimeUnit;

@Slf4j
@MapperScan("com.spring.cloud.order.service.mapper")
@EnableAsync
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudOrderServiceApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCloudOrderServiceApplication.class, args);

//		while (true) {
//			//当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
//			String userName = context.getEnvironment().getProperty("user.name");
//			String userAge = context.getEnvironment().getProperty("user.age");
//
//			//获取当前部署的环境
//			String currentNamespace = context.getEnvironment().getProperty("current.namespace");
//			String currentEnv = context.getEnvironment().getProperty("current.env");
//			String appName = context.getEnvironment().getProperty("spring.application.name");
//
//			System.err.println("current namespace: " + currentNamespace + " current env: " + currentEnv + " application name: " + appName + " user name : " + userName + " age: " + userAge);
//
//			TimeUnit.SECONDS.sleep(10);
//		}
	}

}
