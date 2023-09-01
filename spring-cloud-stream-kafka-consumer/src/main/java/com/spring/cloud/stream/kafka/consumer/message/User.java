package com.spring.cloud.stream.kafka.consumer.message;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/28
 */
@Data
public class User {
    private String userId;
    private String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
