//package com.spring.cloud.admin.server;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public Config hazelcastConfig() {
//        // This map is used to store the events.
//        // It should be configured to reliably hold all the data,
//        // Spring Boot Admin will compact the events, if there are too many
//        MapConfig eventStoreMap = new MapConfig(DEFAULT_NAME_EVENT_STORE_MAP).setInMemoryFormat(InMemoryFormat.OBJECT)
//                .setBackupCount(1)
//                .setMergePolicyConfig(new MergePolicyConfig(PutIfAbsentMergePolicy.class.getName(), 100));
//
//        // This map is used to deduplicate the notifications.
//        // If data in this map gets lost it should not be a big issue as it will atmost
//        // lead to
//        // the same notification to be sent by multiple instances
//        MapConfig sentNotificationsMap = new MapConfig(DEFAULT_NAME_SENT_NOTIFICATIONS_MAP)
//                .setInMemoryFormat(InMemoryFormat.OBJECT).setBackupCount(1)
//                .setEvictionConfig(new EvictionConfig().setEvictionPolicy(EvictionPolicy.LRU)
//                        .setMaxSizePolicy(MaxSizePolicy.PER_NODE))
//                .setMergePolicyConfig(new MergePolicyConfig(PutIfAbsentMergePolicy.class.getName(), 100));
//
//        Config config = new Config();
//        config.addMapConfig(eventStoreMap);
//        config.addMapConfig(sentNotificationsMap);
//        config.setProperty("hazelcast.jmx", "true");
//
//        // WARNING: This setups a local cluster, you change it to fit your needs.
//        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
//        TcpIpConfig tcpIpConfig = config.getNetworkConfig().getJoin().getTcpIpConfig();
//        tcpIpConfig.setEnabled(true);
//        tcpIpConfig.setMembers(singletonList("127.0.0.1"));
//        return config;
//    }
//}
