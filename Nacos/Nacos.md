# Nacos

https://nacos.io/zh-cn/

## 启动服务器
### Linux/Unix/Mac

启动命令(standalone代表着单机模式运行，非集群模式):

```bash
sh startup.sh -m standalone

```

如果您使用的是ubuntu系统，或者运行脚本报错提示[[符号找不到，可尝试如下运行：

```bash
bash startup.sh -m standalone

```

### Windows

启动命令(standalone代表着单机模式运行，非集群模式):

```cmd
cd E:\Nacos\nacos-server-2.1.0\nacos\bin

startup.cmd -m standalone

```

Console: 

http://localhost:8848/nacos/index.html

http://192.168.31.1:8848/nacos/index.html


## 关闭服务器

### Linux/Unix/Mac

```bash
sh shutdown.sh

```

### Windows

```cmd
cd E:\Nacos\nacos-server-2.1.0\nacos

shutdown.cmd

```

或者双击shutdown.cmd运行文件。

