package com.spring.cloud.common.util;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UUIDUtil {
    public static String getUUID() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
