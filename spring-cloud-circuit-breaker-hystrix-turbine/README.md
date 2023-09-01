# spring-cloud-circuit-breaker-hystrix-turbine

## Hystrix Dashboard

### open Dashboard page
 
localhost:8205/hystrix

Cluster via Turbine (default cluster): https://turbine-hostname:port/turbine.stream
Cluster via Turbine (custom cluster): https://turbine-hostname:port/turbine.stream?cluster=[clusterName]
Single Hystrix App: https://hystrix-app:port/actuator/hystrix.stream

#### Cluster via Turbine (default cluster)

http://localhost:8205/turbine.stream

http://localhost:8205/turbine.stream?cluster=default

#### Cluster via Turbine (custom cluster)

http://localhost:8205/turbine.stream?cluster=SPRING-CLOUD-CIRCUIT-BREAKER-HYSTRIX
http://localhost:8205/turbine.stream?cluster=SPRING-CLOUD-CIRCUIT-BREAKER-HYSTRIX2

#### Single Hystrix App

http://localhost:8201/actuator/hystrix.stream
http://localhost:8202/actuator/hystrix.stream

调用以下服务，发生断路，即可在监控页面看到数据

http://localhost:8201/getStores
http://localhost:8202/getOrders

## turbine

http://localhost:8205/turbine.stream

# Clusters Endpoint
