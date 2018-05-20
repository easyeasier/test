package wz.test.springmvc.processor.bean;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

@Slf4j
@Getter
public class KafkaIndexProducer {
    Integer id;
    KafkaProducer<String, String> producer;

    public KafkaIndexProducer(Integer id, Properties config) {
        this.id = id;
        producer = new KafkaProducer<>(config);
    }

    public void produce(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    public void produce(String topic, String key, String value, Partitioner partitioner, Callback callBack) {
        //todo
    }

    public void close() {
        try {
            producer.close();
        } catch (Exception e) {
            log.error("producer close error : id = {}", id, e);
        } finally {
            producer = null;
        }
    }
}
