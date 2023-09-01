package com.spring.cloud.stream.kafka.producer.config;

import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.util.ObjectUtils;

/**
 * <p>Description: 决定message发送到哪个partition的策略</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/19 0019
 */
public class CustomPartitionSelectorClass implements PartitionSelectorStrategy {
    @Override
    public int selectPartition(Object key, int partitionCount) {
        System.out.println("消息载荷的key：" + key + " partitionCount：" + partitionCount);

        if (!ObjectUtils.isEmpty(key)) {
            Long id = (Long) key;

            return id.intValue() % partitionCount;
        }

        return 0;
    }

}