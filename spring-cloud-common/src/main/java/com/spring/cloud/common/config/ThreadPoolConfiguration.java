package com.spring.cloud.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Configuration
public class ThreadPoolConfiguration {

    /**
     * ThreadPoolTaskExecutor taskExecutor 不会覆盖默认配置的 ThreadPoolTaskExecutor
     * ThreadPoolTaskExecutor taskExecutor1 新配置一个线程池
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor1() {
        log.debug("taskExecutor ==========================================");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(8);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setThreadNamePrefix("task-executor1-");
        return taskExecutor;
    }

}
