# artanis-cloud

## spring-cloud-eureka-server

http://localhost:8761


## spring-cloud-admin-server

http://localhost:8762

## log

日志默认配置在 spring-boot.jar 中


## Spring Boot Admin

### Logfile Viewer

application.properties
logging.file.name=/var/log/sample-boot-application.log
logging.pattern.file=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${PID}){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wEx 


## spring-cloud-knife4j-aggregation

https://doc.xiaominfo.com/knife4j/

http://localhost:8061/doc.html

## maven package skip test

cmd中执行

mvn clean package -Dmaven.test.skip=true

mvn -Prelease-nacos clean install -U -Dmaven.test.skip=true

## Prometheus

micro-service1 访问 prometheus endpoint

http://localhost:8081/actuator/prometheus

## kubernetes

$(statefulset name)-$(ordinal)

$(service name).$(namespace).svc.cluster.local

namespace: default

statefulset name: eureka-server

headless service name: eureka-server-headless

service name: eureka-server

eureka-server-0.eureka-server-headless.default.svc.cluster.local

eureka-server-1.eureka-server-headless.default.svc.cluster.local

eureka-server-2.eureka-server-headless.default.svc.cluster.local


KubeSphere 创建工作负载时指定环境变量

JAVA_OPTS

-spring.profiles.active=k8s


node ip:

192.168.66.131

eureka server:

http://192.168.66.131:32372/

http://192.168.66.131:32372/hello


micro-service1:

http://192.168.66.131:31561/getName

gateway:

http://192.168.66.131:30456/service1/getName

## Memory Analyzer (MAT)

解决高版本低JDK无法启动问题

cmd窗口执行:

set PATH=E:\Java\jdk-17.0.1\bin

.\MemoryAnalyzer.exe

### Feign拦截器

1. 编写拦截器

com.spring.cloud.common.interceptor.FeignRequestInterceptor

2. 在user-service中配置Feign调用拦截器

```yaml
feign:
  client:
    config:
      default: # 对所有服务的feign调用有效
        requestInterceptors:
          - com.spring.cloud.common.interceptor.FeignRequestInterceptor
#      spring-cloud-order-service: # 针对特定服务的feign调用有效
#        requestInterceptors:
#          - com.spring.cloud.common.interceptor.FeignRequestInterceptor

```

在user-service中通过feign调用order-server时，发起的请求都会被该拦截器拦截

3. 在order-service中配置请求拦截器

com.spring.cloud.common.interceptor.FeignAuthInterceptor

该拦截器通过WebMvcConfig配置，相当于web请求拦截器

其它服务对order-service服务发起的调用，包括通过feign发起的，请求都会被该拦截器拦截

