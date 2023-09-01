package com.spring.cloud.eureka.microservice1.controller;

/**
 * @author EDZ
 */
public class Result<T> {
    /**
     * 错误码: 0-正常返回，非0-异常返回
     */
    private Integer code;
    /**
     * 错误信息：正常返回时为Null，异常返回必填
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
}
