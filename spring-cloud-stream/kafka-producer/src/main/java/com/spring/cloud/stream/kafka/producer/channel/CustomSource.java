package com.spring.cloud.stream.kafka.producer.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/19 0019
 */
public interface CustomSource {
    String OUTPUT1 = "output1";

    @Output(CustomSource.OUTPUT1)
    MessageChannel output1();

    String OUTPUT2 = "output2";

    @Output(CustomSource.OUTPUT2)
    MessageChannel output2();
}

