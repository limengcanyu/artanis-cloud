
## skywalking

linux:

-javaagent:/Users/rock/IdeaProjects/spring-cloud-alice/skywalking-agent/agent/skywalking-agent.jar
-Dskywalking.agent.service_name=spring-cloud-eureka-microservice1
-Dskywalking.collector.backend_service=localhost:11800

windows:

Git Bash

cd D:/IdeaProjects-MyProject/spring-cloud-alice/spring-cloud-sleuth-skywalking4/target
E:/Java/jdk-14.0.2/bin/java \
-javaagent:D:/IdeaProjects-MyProject/spring-cloud-alice/skywalking/agent/skywalking-agent.jar \
-Dskywalking.agent.service_name=spring-cloud-sleuth-skywalking4 \
-Dskywalking.collector.backend_service=localhost:11800 \
-jar spring-cloud-sleuth-skywalking4-0.0.1-SNAPSHOT.jar

