package com.kafka.avro.controller;

import com.kafka.avro.dto.Employee;
import com.kafka.avro.producer.KafkaAvroProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/events")
public class KafkaAvroController {

    @Autowired
    private KafkaAvroProducer producer;

    @PostMapping
    public String sendMessage(@RequestBody  Employee employee) {
        log.info("......................Sending message .................");
        producer.sendMessage(employee);
        return "Message sent successfully to kafka" + employee.getId();
    }
}
