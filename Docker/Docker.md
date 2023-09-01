# Docker

## 构建镜像

docker build -f spring-cloud-eureka-server/Dockerfile -t limengcanyu/spring-cloud-eureka-server:0.0.1 ./spring-cloud-eureka-server

docker build -f spring-cloud-order-service/Dockerfile -t limengcanyu/spring-cloud-order-service:0.0.1 ./spring-cloud-order-service

docker build -f spring-cloud-user-service/Dockerfile -t limengcanyu/spring-cloud-user-service:0.0.1 ./spring-cloud-user-service

docker build -f spring-cloud-gateway/Dockerfile -t limengcanyu/spring-cloud-gateway:0.0.1 ./spring-cloud-gateway

## 启动容器

### spring-cloud-eureka-server

docker run --name spring-cloud-eureka-server -p 8761:8761 -d limengcanyu/spring-cloud-eureka-server:0.0.1

### spring-cloud-order-service

docker run -d --name spring-cloud-order-service -p 8082:8082 \
  -e SW_AGENT_NAME=spring-cloud-order-service -e SW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.136.135:11800 \
  limengcanyu/spring-cloud-order-service:0.0.1

http://192.168.136.135:8761/

#### 配置skywalking日志收集

docker run -d --name spring-cloud-order-service -p 8082:8082 \
  -e SW_AGENT_NAME=spring-cloud-order-service \
  -e SW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.136.135:11800 \
  -e SW_GRPC_LOG_SERVER_HOST=192.168.136.135,SW_GRPC_LOG_SERVER_PORT=11800 \
  limengcanyu/spring-cloud-order-service:0.0.1

docker logs spring-cloud-order-service

### spring-cloud-user-service

docker run -d --name spring-cloud-user-service -p 8083:8083 \
  -e SW_AGENT_NAME=spring-cloud-user-service -e SW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.136.135:11800 \
  limengcanyu/spring-cloud-user-service:0.0.1

http://192.168.136.135:8083/getUserOrders

#### 配置skywalking日志收集

docker run -d --name spring-cloud-user-service -p 8083:8083 \
  -e SW_AGENT_NAME=spring-cloud-user-service \
  -e SW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.136.135:11800 \
  -e SW_GRPC_LOG_SERVER_HOST=192.168.136.135,SW_GRPC_LOG_SERVER_PORT=11800 \
  limengcanyu/spring-cloud-user-service:0.0.1

docker logs spring-cloud-user-service

### spring-cloud-gateway

docker run --name spring-cloud-gateway -p 8091:8091 -d limengcanyu/spring-cloud-gateway:0.0.1


### 清理资源

#### 单个清理

docker stop spring-cloud-order-service spring-cloud-user-service && docker rm spring-cloud-order-service spring-cloud-user-service

docker rmi limengcanyu/spring-cloud-order-service:0.0.1 limengcanyu/spring-cloud-user-service:0.0.1

docker images | grep limengcanyu

#### 批量清理

docker stop spring-cloud*

docker rm spring-cloud*

docker rmi spring-cloud*

## 进入容器

docker exec -it spring-cloud-eureka-server bash

cat /etc/hosts


docker exec -it spring-cloud-eureka-server cat /etc/hosts

docker logs -f spring-cloud-micro-service1


jps 看下运行的java进程pid 是啥
jinfo -flag InitialHeapSize {pid} 看下 初始堆大小参数是多少


set JAVA_OPTS=-server -Xms1024m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=512m

参数说明：
-server:一定要作为第一个参数，在多个CPU时性能佳
-Xms：初始Heap大小，使用的最小内存,cpu性能高时此值应设的大一些
-Xmx：java heap最大值，使用的最大内存
-XX:PermSize:设定内存的永久保存区域
-XX:MaxPermSize:设定最大内存的永久保存区域
-XX:MaxNewSize:
+XX:AggressiveHeap 会使得 Xms没有意义。这个参数让jvm忽略Xmx参数,疯狂地吃完一个G物理内存,再吃尽一个G的swap。
-Xss：每个线程的Stack大小
-verbose:gc 现实垃圾收集信息
-Xloggc:gc.log 指定垃圾收集日志文件
-Xmn：young generation的heap大小，一般设置为Xmx的3、4分之一
-XX:+UseParNewGC ：缩短minor收集的时间
-XX:+UseConcMarkSweepGC ：缩短major收集的时间

提示：此选项在Heap Size 比较大而且Major收集时间较长的情况下使用更合适。
