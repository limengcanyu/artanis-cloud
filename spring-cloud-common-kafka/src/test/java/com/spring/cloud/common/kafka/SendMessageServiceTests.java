package com.spring.cloud.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.PartitionInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
public class SendMessageServiceTests {
    @Autowired
    private SendMessageService sendMessageService;

    @Test
    public void stringTopic() {
        List<PartitionInfo> partitionInfos = sendMessageService.partitionsFor(KafkaConstant.STRING_TOPIC);
        for (PartitionInfo partitionInfo : partitionInfos) {
            log.debug("partitionInfo.partition: {}", partitionInfo.partition());
        }

        sendMessageService.sendMessage(KafkaConstant.STRING_TOPIC, "send a string message");
    }

    @Test
    public void objectTopic() {
        Map<String, Object> content = new HashMap<>();
        content.put("messageId", "M000001");
        content.put("tenantId", "T000001");
        content.put("companyId", "U000001");
        sendMessageService.sendMessage(KafkaConstant.OBJECT_TOPIC_1, content);
    }

}
