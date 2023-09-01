
#### 启动Seata server

```shell
#Linux
sh seata-server.sh -p 8091 -m file

#Windows
seata-server.bat
```

控制台

http://localhost:7091/

#### Windows启动Seata server报错

```shell
Active code page: 65001
"E:\Seata\seata-server-1.5.2\seata/logs"
"apm-skywalking not enabled"
Unrecognized VM option 'CMSParallelRemarkEnabled'
Error: Could not create the Java Virtual Machine.
Error: A fatal exception has occurred. Program will exit.

E:\Seata\seata-server-1.5.2\seata\bin>
```

```shell
#修改 bin\seata-server.bat

if "%JAVACMD%"=="" set JAVACMD=java

#修改为

if "%JAVACMD%"=="" set JAVACMD=E:\Java\jdk8u332-b09\bin\java
```

#### 异常

```
Caused by: java.lang.reflect.InaccessibleObjectException: Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain) throws java.lang.ClassFormatError accessible: module java.base does not "opens java.lang" to unnamed module @735f7ae5
```

启动JVM参数中添加以下内容

```
--add-opens java.base/java.lang=ALL-UNNAMED
--add-opens java.base/java.util=ALL-UNNAMED
--add-opens java.base/java.nio=ALL-UNNAMED
--add-opens java.base/sun.nio.ch=ALL-UNNAMED
```

#### 测试

1. 分布式事务成功，模拟正常下单、扣库存

   http://localhost:8011/order/placeOrder/commit
   http://localhost:8011/order/placePoint/commit

2. 分布式事务失败，模拟下单成功、扣库存失败，最终同时回滚

   http://localhost:8011/order/placeOrder/rollback
   http://localhost:8011/order/placePoint/rollback

#### 应用配置

1. 每个应用的resource里需要配置一个registry.conf ，demo中与seata-server里的配置相同
2. application.propeties 的各个配置项，注意spring.cloud.alibaba.seata.tx-service-group 是服务组名称，与nacos-config.txt
   配置的service.vgroup_mapping.${your-service-gruop}具有对应关系

