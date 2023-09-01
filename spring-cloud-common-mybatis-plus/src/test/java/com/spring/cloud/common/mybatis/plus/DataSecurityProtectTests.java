package com.spring.cloud.common.mybatis.plus;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.jupiter.api.Test;

public class DataSecurityProtectTests {

    @Test
    public void test() {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        System.out.println(randomKey); // 4b89f4d9719e55b5

        // 随机密钥加密
        String url = "jdbc:h2:mem:test";
        String username = "root";
        String password = "test";

        String urlResult = AES.encrypt(url, randomKey);
        String usernameResult = AES.encrypt(username, randomKey);
        String passwordResult = AES.encrypt(password, randomKey);
        System.out.println("urlResult: " + urlResult + " usernameResult: " + usernameResult + " passwordResult: " + passwordResult);
        // urlResult: xeI4XIc8vudGFJWcCAPre40iV/S0TRTXb+K5HZ9dQr0= usernameResult: HegYHKBKNLXMbcND4mBNmw== passwordResult: lALIx8hGq5t82wvybeo8Rw==
    }

}
