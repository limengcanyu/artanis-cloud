### 单机启动

1. 创建文件 conf/zoo.cfg，添加以下内容

```shell
tickTime=2000
dataDir=/var/lib/zookeeper
clientPort=2181
```

2. 执行启动命令

```shell
#Linux
bin/zkServer.sh start

#Windows
bin\zkServer.cmd

```

