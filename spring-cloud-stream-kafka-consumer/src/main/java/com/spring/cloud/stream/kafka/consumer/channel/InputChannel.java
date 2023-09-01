package com.spring.cloud.stream.kafka.consumer.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * <p>Description: 输入 channel，定义输入 topic</p>
 *
 * @author rock
 * date 2019/06/29
 */
public interface InputChannel {
    String INPUT = "topic";

    /**
     * 接收topic：topic的消息
     *
     * @return
     */
    @Input(InputChannel.INPUT)
    SubscribableChannel input();
}
