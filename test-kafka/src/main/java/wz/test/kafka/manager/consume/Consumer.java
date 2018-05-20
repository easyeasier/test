package wz.test.kafka.manager.consume;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import wz.test.kafka.manager.config.KafkaConsumerConfig;

import java.util.Arrays;
import java.util.Iterator;

@Slf4j
public class Consumer implements Runnable {

    String topic;

    static int count =1 ;
    public Consumer(String topic) {
        this.topic = topic;
    }

    @Override
    public void run() {
        log.warn("consumer start : {}", Thread.currentThread().getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(new KafkaConsumerConfig()
                .getConfig());
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> next = iterator.next();
                log.warn("{} : topic = {}, key = {}, value = {}, partition = {} , offset = {}, count= {}", Thread
                        .currentThread().getName(), next.topic(), next.key(), next.value(), next.partition(), next
                        .offset(), count++);
            }
        }
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer("test");
        new Thread(consumer).start();
    }
}
