package com.spring.cloud.nacos.config.client;

import com.alibaba.druid.pool.DruidDataSource;
import org.jasypt.encryption.StringEncryptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.spring.cloud.nacos.config.client.dao.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class SpringCloudNacosConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNacosConfigClientApplication.class, args);
    }

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private StringEncryptor encryptor;

    @Bean
    DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(encryptor.decrypt(dataSourceProperties.getPassword()));
        return druidDataSource;
    }
}
