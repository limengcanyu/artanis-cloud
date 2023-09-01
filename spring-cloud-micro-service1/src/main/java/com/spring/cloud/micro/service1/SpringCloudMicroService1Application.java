package com.spring.cloud.micro.service1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.Executor;

@Slf4j
@EnableAsync
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudMicroService1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCloudMicroService1Application.class, args);

//        AuthFilter authFilter = context.getBean(AuthFilter.class);
//        log.debug("=========>>> AuthFilter: {}", authFilter);

//        Executor executor = context.getBean(Executor.class);
//        Executor executor = (Executor) context.getBean("taskExecutor");
//        Executor executor1 = (Executor) context.getBean("taskExecutor1");
//        log.debug("=========>>> executor: {}", executor);
//        log.debug("=========>>> executor1: {}", executor1);
//        if (executor instanceof ThreadPoolTaskExecutor taskExecutor) {
//            int activeCount = taskExecutor.getActiveCount();
//            int corePoolSize = taskExecutor.getCorePoolSize();
//            int keepAliveSeconds = taskExecutor.getKeepAliveSeconds();
//            int maxPoolSize = taskExecutor.getMaxPoolSize();
//            int poolSize = taskExecutor.getPoolSize();
//            String threadGroupName = ObjectUtils.isEmpty(taskExecutor.getThreadGroup()) ? null : taskExecutor.getThreadGroup().getName();
//            int threadPriority = taskExecutor.getThreadPriority();
//            log.debug("=========>>> taskExecutor activeCount: {} corePoolSize: {} keepAliveSeconds: {} maxPoolSize: {} poolSize: {} threadGroupName:{} threadPriority: {}",
//                    activeCount, corePoolSize, keepAliveSeconds, maxPoolSize, poolSize, threadGroupName, threadPriority);
//        }

//        if (executor1 instanceof ThreadPoolTaskExecutor taskExecutor) {
//            int activeCount = taskExecutor.getActiveCount();
//            int corePoolSize = taskExecutor.getCorePoolSize();
//            int keepAliveSeconds = taskExecutor.getKeepAliveSeconds();
//            int maxPoolSize = taskExecutor.getMaxPoolSize();
//            int poolSize = taskExecutor.getPoolSize();
//            String threadGroupName = ObjectUtils.isEmpty(taskExecutor.getThreadGroup()) ? null : taskExecutor.getThreadGroup().getName();
//            int threadPriority = taskExecutor.getThreadPriority();
//            log.debug("=========>>> taskExecutor activeCount: {} corePoolSize: {} keepAliveSeconds: {} maxPoolSize: {} poolSize: {} threadGroupName:{} threadPriority: {}",
//                    activeCount, corePoolSize, keepAliveSeconds, maxPoolSize, poolSize, threadGroupName, threadPriority);
//        }

//        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
//            log.debug("beanDefinitionName: {}", beanDefinitionName);
//        }

    }

}
