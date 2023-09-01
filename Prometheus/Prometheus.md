# Prometheus

## prometheus配置 prometheus.yml

```yml
  - job_name: "spring-cloud-eureka-server"
    scrape_interval: 5s
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ["localhost:8761"]

  - job_name: "spring-cloud-admin-server"
    scrape_interval: 5s
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ["localhost:8762"]

  - job_name: "spring-cloud-order-service"
    scrape_interval: 5s
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ["localhost:8082","localhost:8084"]

  - job_name: "spring-cloud-user-service"
    scrape_interval: 5s
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ["localhost:8083"]

```

## prometheus服务端点

#### spring-cloud-eureka-server

http://localhost:8761/actuator/prometheus

#### spring-cloud-admin-server

http://localhost:8762/actuator/prometheus

#### spring-cloud-order-service

http://localhost:8082/actuator/prometheus

http://localhost:8084/actuator/prometheus

#### spring-cloud-user-service

http://localhost:8083/actuator/prometheus
