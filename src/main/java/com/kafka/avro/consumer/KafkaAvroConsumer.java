package com.kafka.avro.consumer;

import com.kafka.avro.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaAvroConsumer {

    @KafkaListener(topics = "${avro.topicName}" , groupId = "avro-group")
    public void readMessages(ConsumerRecord<String, Employee> record) {
        String key = record.key();
        Employee value = record.value();
        log.info("Avro messaged received for Key = " + key + ", Value = " + value);
    }
}
