#!/bin/sh
# 可以使用sh脚本启动，启动命令：sh startup.sh
# SkyWalking Agent配置如下
export SW_AGENT_NAME=springboot-skywalking-wudl # Agent名字,一般使用`spring.application.name`
export SW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.1.180:11800 # 配置 Collector 地址。
export SW_AGENT_SPAN_LIMIT=2000 # 配置链路的最大Span数量，默认为 300。
export JAVA_AGENT=-javaagent:/opt/module/skywalking-apm-bin-es7/agent/skywalking-agent.jar
java $JAVA_AGENT -jar /opt/module/demo/user-center-0.0.1-SNAPSHOT.jar # jar启动
