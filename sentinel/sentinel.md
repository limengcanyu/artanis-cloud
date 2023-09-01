# sentinel

使用如下命令启动控制台：

D:
cd D:\IdeaProjects-MyProject\spring-cloud-alice\sentinel
java -Dserver.port=8081 -Dcsp.sentinel.dashboard.server=localhost:8081 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.7.2.jar

其中 -Dserver.port=8080 用于指定 Sentinel 控制台端口为 8080。

从 Sentinel 1.6.0 起，Sentinel 控制台引入基本的登录功能，默认用户名和密码都是 sentinel。可以参考 鉴权模块文档 配置用户名和密码。

注：若您的应用为 Spring Boot 或 Spring Cloud 应用，您可以通过 Spring 配置文件来指定配置，详情请参考 Spring Cloud Alibaba Sentinel 文档。

localhost:8081

注意：Sentinel 会在客户端首次调用时候进行初始化，开始向控制台发送心跳包。因此需要确保客户端有访问量，才能在控制台上看到监控数据。另外，还是期待大家养成看日志的好习惯，详见 日志文档。

控制台推送规则的日志默认位于控制台机器的 ${user.home}/logs/csp/sentinel-dashboard.log
接入端接收规则日志默认位于接入端机器的 ${user.home}/logs/csp/sentinel-record.log.xxx
接入端 transport server 日志默认位于接入端机器的 ${user.home}/logs/csp/command-center.log.xxx

localhost:8800/echo/service1
localhost:8810/echo/service2
localhost:8820/echo/service3

