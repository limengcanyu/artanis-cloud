# SkyWalking

## 启动

Linux


Windows

```
E:\SkyWalking\skywalking-apm-8.9.1\bin

startup.bat

```

访问地址：

http://localhost:8080/


## 停止

Linux

```
ps -ef | grep skywalking

kill -9 2807 2828

```


## SpringBoot集成SkyWalking

注册中心不需要配置SkyWalking，微服务集群配置即可

SkyWalking使用方式

```
优先级：探针 > JVM配置 > 系统环境变量 > agent.config

一般都使用探针方式，其他方式就不介绍了，配置方式如下：

格式1(推荐)：-javaagent:/path/to/skywalking-agent.jar={config1}={value1},{config2}={value2}

-javaagent:../skywalking-agent.jar=agent.service_name=fw-gateway,collector.backend_service=127.0.0.1:11800

格式2：-Dskywalking.[option1]=[value2]

-javaagent:../skywalking-agent.jar -Dskywalking.agent.service_name=fw-gateway -Dskywalking.collector.backend_service=127.0.0.1:11800

一般配置下面两项即可：

agent.service_name：客户端服务名，在apm系统中显示的服务名称。
collector.backend_service：SW上传的服务地址。

```

启动命令：

java -javaagent:E:/SkyWalking/skywalking-java-agent-8.9.0/skywalking-agent.jar -jar /opt/module/demo/user-center-0.0.1-SNAPSHOT.jar

IDEA VM参数

```
# skywalking-agent.jar的本地磁盘的路径
-javaagent:E:/SkyWalking/skywalking-java-agent-8.9.0/skywalking-agent.jar
# 在skywalking上显示的服务名
-DSW_AGENT_NAME=spring-cloud-order-service
# skywalking的collector服务的IP及端口
-DSW_AGENT_COLLECTOR_BACKEND_SERVICES=localhost:11800

```

spring-cloud-order-service

```
-javaagent:E:/Skywalking/apache-skywalking-java-agent-8.11.0/skywalking-agent.jar
-DSW_AGENT_NAME=spring-cloud-order-service
-DSW_AGENT_COLLECTOR_BACKEND_SERVICES=localhost:11800

```

spring-cloud-user-service

```
-Xms40m -Xmx40m
-javaagent:E:/Skywalking/apache-skywalking-java-agent-8.11.0/skywalking-agent.jar
-DSW_AGENT_NAME=spring-cloud-user-service
-DSW_AGENT_COLLECTOR_BACKEND_SERVICES=localhost:11800

```

## 配置log

引入依赖

		<!-- https://mvnrepository.com/artifact/org.apache.skywalking/apm-toolkit-logback-1.x -->
		<dependency>
			<groupId>org.apache.skywalking</groupId>
			<artifactId>apm-toolkit-logback-1.x</artifactId>
			<version>8.10.0</version>
		</dependency>

项目添加logback.xml

