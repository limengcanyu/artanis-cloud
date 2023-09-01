# spring-cloud-config-server

### 创建本地Git仓库

````
cd $HOME
mkdir config-repo
cd config-repo
git init .
echo info.foo: bar > application.properties
git add -A .
git commit -m "Add application.properties"
````

Config Server 启动以后，可以通过HTTP服务获取属性资源，资源形式如下：

````
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties

application 对应 spring.config.name
profile 对应激活配置
label 对应可选的Git分支，默认为 master

例：
http://localhost:8970/spring-cloud-config-client/default 查看 spring-cloud-config-client.yml
http://localhost:8970/spring-cloud-config-client/dev     查看 spring-cloud-config-client-dev.yml
````
