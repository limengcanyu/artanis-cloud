docker build -t jiangxingfeng/spring-cloud-eureka-server:0.0.1-SNAPSHOT .


pod hostname: $(statefulset name)-$(ordinal)

pod domain: $(service name).$(namespace).svc.cluster.local

namespace: default

service name: eureka-server

statefulset name: eureka-server

eureka-server-0.eureka-server.default.svc.cluster.local

eureka-server-1.eureka-server.default.svc.cluster.local

eureka-server-2.eureka-server.default.svc.cluster.local


