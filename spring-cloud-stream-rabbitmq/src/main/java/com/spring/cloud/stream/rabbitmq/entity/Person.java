package com.spring.cloud.stream.rabbitmq.entity;

/**
 * description:
 *
 * @author rock
 * time 2020/7/16 0016 11:19
 */
public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
