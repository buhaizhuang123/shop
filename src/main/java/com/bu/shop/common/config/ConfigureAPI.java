package com.bu.shop.common.config;

/**
 * @author haizhuangbu
 * @date 2022/3/15 10:43 上午
 * @mark ConfigureAPI
 */
public class ConfigureAPI {

    public interface KafkaProperties{
        String ZK = "localhost:2181";
        String GROUP_ID = "test_group1";
        String TOPIC = "test2";
        String BROKER_LIST = "localhost:9092";
        int BUFFER_SIZE = 64 * 1024;
        int TIMEOUT = 20000;
        int INTERVAL = 10000;
    }

}
