package com.spring.cloud.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringCloudCommonApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCloudCommonApplication.class, args);

//        Executor executor = context.getBean(Executor.class);
//        log.debug("=========>>> Executor: {}", executor);
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

	}

}
