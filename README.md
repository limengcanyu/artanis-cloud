# spring-cloud-alice

````
spring-cloud-eureka-server                   localhost:8761
spring-cloud-admin-server                    localhost:8770
spring-cloud-gateway                         localhost:8780   localhost:8781   localhost:8782
spring-cloud-zuul                            localhost:8790
spring-cloud-microservice1                   localhost:8800   localhost:8801   localhost:8802
spring-cloud-microservice2                   localhost:8810
spring-cloud-microservice3                   localhost:8820
spring-cloud-security                        localhost:8830
spring-cloud-zipkin-server                   localhost:9411
spring-cloud-sleuth                          localhost:8850
spring-cloud-sleuth-zipkin1                  localhost:8860
spring-cloud-sleuth-zipkin2                  localhost:8870
spring-cloud-stream-kafka                    localhost:8880
spring-cloud-stream-kafka-consumer           localhost:8890
spring-cloud-stream-kafka-producer           localhost:8900
spring-cloud-stream-kafka-streams            localhost:8910
spring-cloud-stream-kafka-streams-consumer   localhost:8920
spring-cloud-stream-kafka-streams-producer   localhost:8930
spring-cloud-stream-rabbitmq                 localhost:8940
spring-cloud-stream-rabbitmq-consumer        localhost:8950
spring-cloud-stream-rabbitmq-producer        localhost:8960
spring-cloud-config-server                   localhost:8970
spring-cloud-config-client                   localhost:8980
````

Zuul验证token是否存在，后端微服务解析token，因为要获取租户信息，分工验证，就不用多次验证token。

## Disabling Ribbon with Eureka Server and Client starters

spring-cloud-starter-netflix-eureka-server and spring-cloud-starter-netflix-eureka-client come along with a spring-cloud-starter-netflix-ribbon. Since Ribbon load-balancer is now in maintenance mode, we suggest switching to using the Spring Cloud LoadBalancer, also included in Eureka starters, instead.

In order to that, you can set the value of spring.cloud.loadbalancer.ribbon.enabled property to false.

You can then also exclude ribbon-related dependencies from Eureka starters in your build files, like so:

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-ribbon</artifactId>
        </exclusion>
        <exclusion>
            <groupId>com.netflix.ribbon</groupId>
            <artifactId>ribbon-eureka</artifactId>
        </exclusion>
    </exclusions>
</dependency>
