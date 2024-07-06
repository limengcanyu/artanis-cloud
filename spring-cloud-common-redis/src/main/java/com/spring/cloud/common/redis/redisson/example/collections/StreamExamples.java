/**
 * Copyright (c) 2016-2019 Nikita Koksharov
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spring.cloud.common.redis.redisson.example.collections;

import org.redisson.Redisson;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.redisson.api.StreamMessageId;
import org.redisson.api.stream.StreamAddArgs;
import org.redisson.api.stream.StreamCreateGroupArgs;
import org.redisson.api.stream.StreamReadArgs;

import java.time.Duration;
import java.util.Map;

public class StreamExamples {

    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RStream<String, String> stream = redisson.getStream("test");
        stream.createGroup(StreamCreateGroupArgs.name("testGroup").id(new StreamMessageId(1234567890L)).makeStream());

        StreamMessageId id1 = StreamMessageId.AUTO_GENERATED.autogenerateSequenceId();
        StreamMessageId id2 = StreamMessageId.AUTO_GENERATED.autogenerateSequenceId();
        stream.add(id1, StreamAddArgs.entry("15", "37"));
        stream.add(id2, StreamAddArgs.entries("15", "37", "23", "43"));

        // contains 2 elements
        Map<StreamMessageId, Map<String, String>> map1 = stream.read(StreamReadArgs.greaterThan(new StreamMessageId(1234567890L)));

        // ack messages
        stream.ack("testGroup", id1, id2);

        stream.add(id1, StreamAddArgs.entry("25", "57"));
        stream.add(id2, StreamAddArgs.entries("25", "57", "33", "63"));

        // contains next 2 elements
        Map<StreamMessageId, Map<String, String>> map2 = stream.read(StreamReadArgs.greaterThan(new StreamMessageId(1234567890L)).count(100).timeout(Duration.ofSeconds(5)));

//        PendingResult pi = stream.listPending("testGroup");

        redisson.shutdown();
    }

}
