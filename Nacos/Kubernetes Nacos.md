
git clone https://github.com/nacos-group/nacos-k8s.git

快速启动,没有使用持久化卷,可能存在数据丢失风险:

cd nacos-k8s
chmod +x quick-startup.sh
./quick-startup.sh

nacos-k8s\deploy\nacos\nacos-quick-start.yaml

单机部署修改限制：

```yaml
---
apiVersion: v1
kind: Service
metadata:
  name: nacos-headless
  labels:
    app: nacos-headless
spec:
  type: ClusterIP
  clusterIP: None
  ports:
    - port: 8848
      name: server
      targetPort: 8848
    - port: 9848
      name: client-rpc
      targetPort: 9848
    - port: 9849
      name: raft-rpc
      targetPort: 9849
    ## 兼容1.4.x版本的选举端口
    - port: 7848
      name: old-raft-rpc
      targetPort: 7848
  selector:
    app: nacos
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: nacos-cm
data:
  mysql.db.name: "nacos_devtest"
  mysql.port: "3306"
  mysql.user: "nacos"
  mysql.password: "nacos"
---
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: nacos
spec:
  serviceName: nacos-headless
  replicas: 3
  template:
    metadata:
      labels:
        app: nacos
      annotations:
        pod.alpha.kubernetes.io/initialized: "true"
    spec:
      affinity: // 去掉该部分
        podAntiAffinity:
          requiredDuringSchedulingIgnoredDuringExecution:
            - labelSelector:
                matchExpressions:
                  - key: "app"
                    operator: In
                    values:
                      - nacos
              topologyKey: "kubernetes.io/hostname"
      containers:
        - name: k8snacos
          imagePullPolicy: Always
          image: nacos/nacos-server:latest
          resources:
            requests:
              memory: "2Gi" // 修改为 1Gi
              cpu: "500m" // 修改为 250m
          ports:
            - containerPort: 8848
              name: client
            - containerPort: 9848
              name: client-rpc
            - containerPort: 9849
              name: raft-rpc
            - containerPort: 7848
              name: old-raft-rpc
          env:
            - name: NACOS_REPLICAS
              value: "3"
            - name: MYSQL_SERVICE_DB_NAME
              valueFrom:
                configMapKeyRef:
                  name: nacos-cm
                  key: mysql.db.name
            - name: MYSQL_SERVICE_PORT
              valueFrom:
                configMapKeyRef:
                  name: nacos-cm
                  key: mysql.port
            - name: MYSQL_SERVICE_USER
              valueFrom:
                configMapKeyRef:
                  name: nacos-cm
                  key: mysql.user
            - name: MYSQL_SERVICE_PASSWORD
              valueFrom:
                configMapKeyRef:
                  name: nacos-cm
                  key: mysql.password
            - name: NACOS_SERVER_PORT
              value: "8848"
            - name: NACOS_APPLICATION_PORT
              value: "8848"
            - name: PREFER_HOST_MODE
              value: "hostname"
            - name: NACOS_SERVERS
              value: "nacos-0.nacos-headless.default.svc.cluster.local:8848 nacos-1.nacos-headless.default.svc.cluster.local:8848 nacos-2.nacos-headless.default.svc.cluster.local:8848"
  selector:
    matchLabels:
      app: nacos


```


新增service

```yaml
kind: Service
apiVersion: v1
metadata:
  name: nacos
  namespace: default
  labels:
    app: nacos
  annotations:
    kubesphere.io/creator: admin
spec:
  ports:
    - name: http-8848
      protocol: TCP
      port: 8848
      targetPort: 8848
      nodePort: 31419
  selector:
    app: nacos
  clusterIP: 10.233.57.47
  clusterIPs:
    - 10.233.57.47
  type: NodePort
  sessionAffinity: None
  externalTrafficPolicy: Cluster

```

访问: node ip + service暴露端口

http://192.168.66.131:31419/nacos

user/password: nacos/nacos


