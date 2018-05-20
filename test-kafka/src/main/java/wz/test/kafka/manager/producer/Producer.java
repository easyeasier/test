package wz.test.kafka.manager.producer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import wz.test.kafka.manager.config.KafkaConfig;

@Getter
@Setter
@Slf4j
public class Producer {
    Integer id;
    KafkaProducer<String, String> producer;

    public Producer(Integer id, KafkaConfig config) {
        this.id = id;
        producer = new KafkaProducer<>(config.getConfig());
    }

    public void produce(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record, (RecordMetadata metadata, Exception exception) -> {
            if (metadata != null && metadata.hasOffset()) {
                log.info("send msg success : topic = {}, key = {}, value = {}, partition = {} , offset= {}, " +
                        "producer_id = {}", topic, key, value, metadata.partition(), metadata.offset(), id);
            } else {
                log.error("send msg error : topic = {}， key ={} ,value = {}, ", topic, key, value, exception);
            }
        });
    }

    public void close() {

        try {
            producer.close();
        } catch (Exception e) {
            log.error("producer close error : ", e);
        } finally {
            producer = null;
        }

    }
}
