# Arthas

https://arthas.aliyun.com/doc/watch.html

## 启动arthas

java -jar arthas-boot.jar

## watch

### 观察函数调用返回时的参数、this对象和返回值

观察表达式，默认值是{params, target, returnObj}

$ watch demo.MathGame primeFactors -x 2

### 观察函数调用入口的参数和返回值

$ watch demo.MathGame primeFactors "{params,returnObj}" -x 2 -b

### 同时观察函数调用前和函数返回后

$ watch demo.MathGame primeFactors "{params,target,returnObj}" -x 2 -b -s -n 2

## 退出arthas

如果只是退出当前的连接，可以用 quit 或者 exit 命令。Attach到目标进程上的arthas还会继续运行，端口会保持开放，下次连接时可以直接连接上。

如果想完全退出arthas，可以执行 stop 命令。
