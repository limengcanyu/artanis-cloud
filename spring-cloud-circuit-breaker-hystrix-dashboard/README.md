# spring-cloud-circuit-breaker-hystrix-dashboard

## Hystrix Dashboard

### open Dashboard page
 
localhost:8200/hystrix

Cluster via Turbine (default cluster): https://turbine-hostname:port/turbine.stream
Cluster via Turbine (custom cluster): https://turbine-hostname:port/turbine.stream?cluster=[clusterName]
Single Hystrix App: https://hystrix-app:port/actuator/hystrix.stream

https://localhost:8200/actuator/hystrix.stream


http://localhost:8201/actuator/hystrix.stream

http://localhost:8202/actuator/hystrix.stream

调用以下服务，发生断路，即可在监控页面看到数据

http://localhost:8201/getStores
http://localhost:8202/getStores
