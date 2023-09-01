/**
 * Copyright (c) 2016-2019 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spring.cloud.common.redis.redisson.example.objects;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;

public class BloomFilterExamples {

    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("bloomFilter");
        // 初始化布隆过滤器，预计统计元素数量为10000000，期望误差率为0.03
        bloomFilter.tryInit(100_000_000, 0.03);
        
        bloomFilter.add("a");
        bloomFilter.add("b");
        bloomFilter.add("c");
        bloomFilter.add("d");
        
        bloomFilter.getExpectedInsertions();
        bloomFilter.getFalseProbability();
        bloomFilter.getHashIterations();
        
        bloomFilter.contains("a");
        
        bloomFilter.count();
        
        redisson.shutdown();
    }
    
}
