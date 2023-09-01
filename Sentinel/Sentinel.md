# Sentinel

https://sentinelguard.io/

https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel

## Start the Dashboard

Sentinel dashboard is a standard SpringBoot application, and you can run the JAR file in the Spring Boot mode.

```bash
cd E:\Sentinel

#cmd中执行

E:\Java\jdk8u332-b09\bin\java -Dserver.port=8000 -Dcsp.sentinel.dashboard.server=localhost:8000 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.4.jar

```

If there is conflict with the 8080 port, you can use -Dserver.port=new port to define a new port.

http://localhost:8000

默认用户名和密码都是 sentinel


## 限流规则因素

resource：资源名，即限流规则的作用对象
count: 限流阈值
grade: 限流阈值类型（QPS 或并发线程数）
limitApp: 流控针对的调用来源，若为 default 则不区分调用来源
strategy: 调用关系限流策略
controlBehavior: 流量控制效果（直接拒绝、Warm Up、匀速排队）

## 熔断降级规则（DegradeRule）属性

resource: 资源名，即规则的作用对象
grade: 熔断策略，支持慢调用比例/异常比例/异常数策略	慢调用比例  0: average RT, 1: exception ratio, 2: exception count
count: 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
timeWindow: 熔断时长，单位为 s
minRequestAmount: 熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入）	5
statIntervalMs: 统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入）	1000 ms
slowRatioThreshold: 慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）	

## gateway 限流

jvm启动参数

```shell
-Dcsp.sentinel.app.type=1 -Dcsp.sentinel.dashboard.server=localhost:8000 -Dproject.name=spring-cloud-gateway
```

这种方式启动，Sentinel控制台才能看到应用
