
localhost:8761

使用eureka注册中心时部署 spring boot admin

两种部署方式：

1. 使用 Eureka Server 部署 Spring Boot Admin，必须注册自身实例，启动时会报错，但没关系
2. 使用 Eureka Client 部署 Spring Boot Admin

```
Eureka Server   spring-cloud-eureka-server
Eureka Client   spring-boot-admin-eureka-server    Spring Boot Admin Server
Eureka Client   spring-boot-admin-eureka-client1   Spring Boot Admin Client
Eureka Client   spring-boot-admin-eureka-client2   Spring Boot Admin Client
```








