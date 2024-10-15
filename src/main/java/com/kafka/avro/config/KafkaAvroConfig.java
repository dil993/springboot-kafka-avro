package com.kafka.avro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class KafkaAvroConfig {

    @Value("${avro.topicName}")
    private String topicName;
    @Bean
    public NewTopic createEmployeeTopic() {
        log.info("Topic Name -------------------- "+ topicName);
        return new NewTopic(topicName, 3, (short) 1);
    }
}
