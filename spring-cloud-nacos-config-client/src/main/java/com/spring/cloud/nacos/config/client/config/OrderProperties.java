package com.spring.cloud.nacos.config.client.config;

import lombok.Data;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * ConfigurationProperties 配置类
 * </p>
 *
 * @author rock.jxf
 * @date 2020/11/24 16:36
 */
@Data
@ConfigurationProperties(prefix = "order", ignoreInvalidFields = true)
@Configuration
public class OrderProperties {

    @Autowired
    private StringEncryptor encryptor;

    private String id;

    private String password;

    public String getPassword() {
        return encryptor.decrypt(password);
    }
}
