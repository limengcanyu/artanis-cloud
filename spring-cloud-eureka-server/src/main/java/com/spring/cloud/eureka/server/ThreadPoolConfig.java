//package com.spring.cloud.eureka.server;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//@Slf4j
//@Configuration
//public class ThreadPoolConfig {
//
//    @Bean
//    public ThreadPoolTaskExecutor taskExecutor() {
//        log.debug("taskExecutor ==========================================");
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        taskExecutor.setCorePoolSize(8);
//        taskExecutor.setMaxPoolSize(20);
//        taskExecutor.setKeepAliveSeconds(60);
//        taskExecutor.setQueueCapacity(100);
//        taskExecutor.setThreadNamePrefix("custom");
//        return taskExecutor;
//    }
//
//}
//
