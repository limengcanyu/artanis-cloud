# spring-cloud-common-mybatis-plus

## H2

web console

http://localhost/h2-database

## Mybatis Plus generate code using H2 

1. start application
2. 进入h2 web console http://localhost/h2-database
3. create table using schema-h2.sql
4. stop application
5. run MybatisPlusNewCodeGenerator

## swagger-ui

http://localhost:8080/swagger-ui.html

## 数据安全保护

// Jar 启动参数（ idea 设置 Program arguments , 服务器可以设置为启动环境变量 ）
--mpw.key=4b89f4d9719e55b5

@SpringBootTest单元测试不能使用，@MybatisPlusTest单元测试可使用


