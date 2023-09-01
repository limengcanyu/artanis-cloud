# spring-cloud-circuit-breaker-hystrix

## Health Indicator

localhost:8202/actuator/health

## Hystrix Metrics Stream

localhost:8202/actuator/hystrix.stream

没有feign
Caused by: java.lang.IllegalStateException: No Feign Client for loadBalancing defined. Did you forget to include spring-cloud-starter-netflix-ribbon?