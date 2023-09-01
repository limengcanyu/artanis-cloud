package com.spring.cloud.stream.kafka.producer.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <p>Description: 输出 channel， 定义输出topic</p>
 *
 * @author rock
 * date 2019/06/29
 */
public interface OutputChannel {
    String OUTPUT = "topic";

    /**
     * 发送消息到topic：topic
     *
     * @return
     */
    @Output(OutputChannel.OUTPUT)
    MessageChannel output();
}
