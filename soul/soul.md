
soul-admin 9095

soul-bootstrap 9195

eureka-server 8761

1. Download soul-admin.jar, then run it with arguments.

power shell 中执行，cmd中执行报错

E:
cd E:\IdeaProjects\spring-cloud-alice\soul
java -jar soul-admin.jar --spring.datasource.url="jdbc:mysql://192.168.203.132:3306/soul?useUnicode=true&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true" --spring.datasource.username='root' --spring.datasource.password='.PassW0rd,321'

启动后访问admin地址

http://localhost:9095/index.html

default username：admin password: 123456.




