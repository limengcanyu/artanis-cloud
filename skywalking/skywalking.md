# skywalking

Docker Images for convenience

Base, https://hub.docker.com/r/apache/skywalking-base
SkyWalking OAP Server, https://hub.docker.com/r/apache/skywalking-oap-server
SkyWalking UI, https://hub.docker.com/r/apache/skywalking-ui


## docker

docker search skywalking

docker pull apache/skywalking-oap-server 
docker pull apache/skywalking-ui

docker run --name skywalking -d -p 11800:11800 -p 12800:12800 --restart always \
  --link elasticsearch:elasticsearch -e TZ=Asia/Shanghai -e SW_STORAGE=elasticsearch \
  -e SW_STORAGE_ES_CLUSTER_NODES=elasticsearch:9200 apache/skywalking-oap-server 
 
docker run --name skywalking-ui -d -p 7070:8080 --link skywalking:skywalking -e TZ=Asia/Shanghai \
  -e SW_OAP_ADDRESS=skywalking:12800 --restart always apache/skywalking-ui

UI地址

http://内网宿主机ip:7070

连接地址为

内网宿主机ip:11800


解压apache-skywalking-apm-8.2.0.tar.gz

## start

linux:

/Users/rock/Downloads/apache-skywalking-apm-bin/bin/startup.sh

windows:

D:/IdeaProjects-MyProject/spring-cloud-alice/skywalking/apache-skywalking-apm-8.2.0/bin/startup.bat

log

D:\apache-skywalking-apm-8.2.0\logs

tail -fn 300 skywalking-oap-server.log
tail -fn 300 webapp.log

ui

localhost:8080

## set Backend storage for MySQL

将 mysql-connector-java-8.0.21.jar 放到 D:\apache-skywalking-apm-8.1.0\apache-skywalking-apm-bin\oap-libs 中

修改 D:\apache-skywalking-apm-8.1.0\apache-skywalking-apm-bin\config\application.yml

需事先建数据库 swtest

``````
storage:
  selector: ${SW_STORAGE:mysql}
  mysql:
    properties:
      jdbcUrl: ${SW_JDBC_URL:"jdbc:mysql://localhost:3306/swtest?serverTimezone=Asia/Shanghai"}
      dataSource.user: ${SW_DATA_SOURCE_USER:root}
      dataSource.password: ${SW_DATA_SOURCE_PASSWORD:root@1234}
      dataSource.cachePrepStmts: ${SW_DATA_SOURCE_CACHE_PREP_STMTS:true}
      dataSource.prepStmtCacheSize: ${SW_DATA_SOURCE_PREP_STMT_CACHE_SQL_SIZE:250}
      dataSource.prepStmtCacheSqlLimit: ${SW_DATA_SOURCE_PREP_STMT_CACHE_SQL_LIMIT:2048}
      dataSource.useServerPrepStmts: ${SW_DATA_SOURCE_USE_SERVER_PREP_STMTS:true}
    metadataQueryMaxSize: ${SW_STORAGE_MYSQL_QUERY_MAX_SIZE:5000}
``````

## Alarm

alarm rule definition file: config/alarm-settings.yml

## 服务启动JVM参数

linux:

-javaagent:/Users/rock/IdeaProjects/spring-cloud-alice/skywalking-agent/agent/skywalking-agent.jar
-Dskywalking.agent.service_name=spring-cloud-sleuth-skywalking1
-Dskywalking.collector.backend_service=localhost:11800

windows:

Git Bash

cd D:/IdeaProjects-MyProject/spring-cloud-alice/spring-cloud-sleuth-skywalking1/target
E:/Java/jdk-14.0.2/bin/java \
-javaagent:D:/IdeaProjects-MyProject/spring-cloud-alice/skywalking/apache-skywalking-apm-8.2.0/agent/skywalking-agent.jar \
-Dskywalking.agent.service_name=spring-cloud-sleuth-skywalking1 \
-Dskywalking.collector.backend_service=localhost:11800 \
-jar spring-cloud-sleuth-skywalking1-0.0.1-SNAPSHOT.jar


IDEA VM参数
-javaagent:D:/IdeaProjects-MyProject/spring-cloud-alice/skywalking/apache-skywalking-apm-8.2.0/agent/skywalking-agent.jar
-Dskywalking.agent.service_name=spring-cloud-sleuth-skywalking1
-Dskywalking.collector.backend_service=localhost:11800

