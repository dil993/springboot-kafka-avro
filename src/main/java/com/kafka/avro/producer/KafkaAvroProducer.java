package com.kafka.avro.producer;

import com.kafka.avro.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaAvroProducer {

    @Value("${avro.topicName}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Employee> template;



    public void sendMessage(Employee employee) {

       log.info("Sending message {} to topic {}", employee, topicName);
       CompletableFuture<SendResult<String, Employee>> future= template.send(topicName, UUID.randomUUID().toString(),employee);
       future.whenComplete(
               (result, ex) -> {
                   if (ex != null) {
                       log.error("Unable to send message : {}", ex.getMessage());
                   }
                   else {
                       log.info("Sent Message [{}] With offset of [{}]", employee, result.getRecordMetadata().offset());
                   }
               });
    }

}
