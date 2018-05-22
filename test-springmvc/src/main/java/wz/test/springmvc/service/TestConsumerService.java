package wz.test.springmvc.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Slf4j
public class TestConsumerService {
    public static void consume(ConsumerRecord<String, String> record) {
        log.info("consume record : topic={}, key={}, value={}", record.topic(), record.key(), record.value());
    }
}
