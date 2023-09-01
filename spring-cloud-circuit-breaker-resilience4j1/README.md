# spring-cloud-circuit-breaker-resilience4j1

## spring retry

添加 spring-boot-starter-aop 依赖
CircuitBreakersConfig 中定义 RetryTemplate 即可，
SpringCloudCircuitBreakerResilience4j1Application 中添加 @EnableRetry
在需要执行重试的方法上添加 @Retryable(value = Exception.class, maxAttempts = 5) // 此处配置的重试次数有效

