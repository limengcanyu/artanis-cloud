package com.spring.cloud.nacos.config.client;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

@SpringBootTest
class SpringCloudNacosConfigClientApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    void encode() {
        String password = "samuro001002";
        System.out.println(encryptor.encrypt(password));
        // viZXzfXv673GQHcsjRZy8p8R9qQmCzzFsU3qXXkBECk=

        password = "artanis001002";
        System.out.println(encryptor.encrypt(password));
        // 2F34BZlow/Ots+XhwRGjfKUohhmPb95xxZ8NDGu6LGE=
    }

}
