### SpringBoot应用添加启动参数

```yaml
    spec:
      containers:
        - name: eureka-server
          image: 'limengcanyu/spring-cloud-eureka-server:0.0.1'
          ports:
            - name: http-8761
              containerPort: 8761
              protocol: TCP
#          args: # 有效
#            - '--spring.profiles.active=k8s'
          env:
            - name: JAVA_TOOL_OPTIONS # 有效
              value: >-
                -Dspring.profiles.active=common,k8s
                -XX:+UseG1GC -Xms2G -Xmx2G -Xss256k -XX:MaxGCPauseMillis=300 
                -Xloggc:/logs/gc.log -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
                -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp

#            - name: JAVA_OPTS # 无效
#              value: >-
#                -Dspring.profiles.active=k8s
#                -XX:+UseG1GC -Xms2G -Xmx2G -Xss256k -XX:MaxGCPauseMillis=300 
#                -Xloggc:/logs/gc.log -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
#                -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp

```

### ConfigMap

kubectl create configmap eureka-config --from-file=eureka-config.yml

kubectl get configmap eureka-config -o yaml

### 部署命令

```shell
#创建镜像
docker build -f spring-cloud-eureka-server/Dockerfile -t limengcanyu/spring-cloud-eureka-server:0.0.1 ./spring-cloud-eureka-server

docker build -f spring-cloud-order-service/Dockerfile -t limengcanyu/spring-cloud-order-service:0.0.1 ./spring-cloud-order-service

docker build -f spring-cloud-user-service/Dockerfile -t limengcanyu/spring-cloud-user-service:0.0.1 ./spring-cloud-user-service

docker build -f spring-cloud-gateway/Dockerfile -t limengcanyu/spring-cloud-gateway:0.0.1 ./spring-cloud-gateway

#清理none镜像
docker image prune

docker image ls | grep limengcanyu


#使用gzip创建备份

rm -f spring-cloud-eureka-server.tar.gz

docker save limengcanyu/spring-cloud-eureka-server:0.0.1 | gzip > spring-cloud-eureka-server.tar.gz


rm -f spring-cloud-order-service.tar.gz

docker save limengcanyu/spring-cloud-order-service:0.0.1 | gzip > spring-cloud-order-service.tar.gz


rm -f spring-cloud-user-service.tar.gz

docker save limengcanyu/spring-cloud-user-service:0.0.1 | gzip > spring-cloud-user-service.tar.gz


rm -f spring-cloud-gateway.tar.gz

docker save limengcanyu/spring-cloud-gateway:0.0.1 | gzip > spring-cloud-gateway.tar.gz

ls -sh spring-cloud*.tar.gz


#从129机器上复制文件到本地 ############################################
#spring-cloud-eureka-server
rm -f spring-cloud-eureka-server.tar.gz

scp root@192.168.242.129:/home/rock/spring-cloud-eureka-server.tar.gz /home/rock/spring-cloud-eureka-server.tar.gz

#spring-cloud-order-service
rm -f spring-cloud-order-service.tar.gz

scp root@192.168.242.129:/home/rock/spring-cloud-order-service.tar.gz /home/rock/spring-cloud-order-service.tar.gz

#spring-cloud-user-service
rm -f spring-cloud-user-service.tar.gz

scp root@192.168.242.129:/home/rock/spring-cloud-user-service.tar.gz /home/rock/spring-cloud-user-service.tar.gz

#spring-cloud-gateway
rm -f spring-cloud-gateway.tar.gz

scp root@192.168.242.129:/home/rock/spring-cloud-gateway.tar.gz /home/rock/spring-cloud-gateway.tar.gz

ls -sh spring-cloud*.tar.gz


#删除镜像
docker rmi limengcanyu/spring-cloud-eureka-server:0.0.1

docker rmi limengcanyu/spring-cloud-order-service:0.0.1

docker rmi limengcanyu/spring-cloud-user-service:0.0.1

docker rmi limengcanyu/spring-cloud-gateway:0.0.1

docker image ls | grep limengcanyu


#恢复镜像
docker load < spring-cloud-eureka-server.tar.gz

docker load < spring-cloud-order-service.tar.gz

docker load < spring-cloud-user-service.tar.gz

docker load < spring-cloud-gateway.tar.gz

docker image ls | grep limengcanyu

```