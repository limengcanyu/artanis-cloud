## 重要变更

- Spring Cloud 2020 版本 基于 Spring Boot 2.4 构建，不支持低版本

- Spring Cloud 2020 已全面移除以下过期依赖

```
spring-cloud-netflix-archaius
spring-cloud-netflix-concurrency-limits
spring-cloud-netflix-core
spring-cloud-netflix-dependencies
spring-cloud-netflix-hystrix
spring-cloud-netflix-hystrix-contract
spring-cloud-netflix-hystrix-dashboard
spring-cloud-netflix-hystrix-stream
spring-cloud-netflix-ribbon
spring-cloud-netflix-sidecar
spring-cloud-netflix-turbine
spring-cloud-netflix-turbine-stream
spring-cloud-netflix-zuul
spring-cloud-starter-netflix-archaius
spring-cloud-starter-netflix-hystrix
spring-cloud-starter-netflix-hystrix-dashboard
spring-cloud-starter-netflix-ribbon
spring-cloud-starter-netflix-turbine
spring-cloud-starter-netflix-turbine-stream
spring-cloud-starter-netflix-zuul
```

- Spring Cloud Commons 默认关闭 Bootstrap 启动引导， 如需兼容之前版本请添加 `spring-cloud-starter-bootstrap`

- Spring Cloud Commons 新增 Spring Cloud LoadBalancer 正式 GA 标识生产可用

- Spring Cloud Security 项目已经完全被移除

- Spring Cloud Openfeign 支持 Spring Cloud CircuitBreakers 抽象适配，支持 Spring Cloud LoadBalancer

- Spring Cloud Gateway 基于 Spring Cloud LoadBalancer 重构，不再支持 Ribbon


## 依赖更新

| Module | Version |
| --- | --- |
| Spring Cloud Circuitbreaker | 2.0.0 |
| Spring Cloud Contract | 3.0.0 |
| Spring Cloud Kubernetes | 2.0.0 |
| Spring Cloud Commons | 3.0.0 |
| Spring Cloud Openfeign | 3.0.0 |
| Spring Cloud Cloudfoundry | 3.0.0 |
| Spring Cloud Security | 3.0.0 |
| Spring Cloud Bus | 3.0.0 |
| Spring Cloud Cli | 3.0.0 |
| Spring Cloud Zookeeper | 3.0.0 |
| Spring Cloud Sleuth | 3.0.0 |
| Spring Cloud Consul | 3.0.0 |
| Spring Cloud Starter Build | 2020.0.0 |
| Spring Cloud Gateway | 3.0.0 |
| Spring Cloud Netflix | 3.0.0 |
| Spring Cloud Vault | 3.0.0 |
| Spring Cloud Config | 3.0.0 |
| Spring Cloud Task | 2.3.0 |

