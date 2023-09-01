
## skywalking

-javaagent:/Users/rock/IdeaProjects/spring-cloud-alice/skywalking-agent/agent/skywalking-agent.jar
-Dskywalking.agent.service_name=spring-cloud-eureka-server
-Dskywalking.collector.backend_service=localhost:11800

注：
Eureka Server不可用后，已经注册的服务还可以通过Feign相互调用，因为注册过的服务都会缓存注册中心的注册信息；
如果发起调用的服务方重启，则不能再调用

