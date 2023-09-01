package com.spring.cloud.common.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.spring.cloud.common.entity.User;

public class ThreadContextUtil {
    private static final TransmittableThreadLocal<User> context = new TransmittableThreadLocal<>();

    public static void setUser(User user) {
        context.set(user);
    }

    public static void clearUser(User user) {
        context.remove();
    }

    public static User getUser(){
        return context.get();
    }
}
