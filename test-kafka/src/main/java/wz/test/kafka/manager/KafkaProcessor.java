package wz.test.kafka.manager;

import lombok.extern.slf4j.Slf4j;
import wz.test.kafka.manager.producer.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class KafkaProcessor {
    private static ExecutorService executorService = new ThreadPoolExecutor(20, 20, 1, TimeUnit.MINUTES, new
            LinkedBlockingQueue<>(3000));

    private KafkaProducerManager producerManager;

    public KafkaProcessor() {
        producerManager = new KafkaProducerManager();
    }

    public void process(String topic, String key, String value) {
        executorService.execute(new Task(producerManager, topic, key, value));
    }

    public static class Task implements Runnable {

        KafkaProducerManager manager;
        String topic;
        String key;
        String value;

        public Task(KafkaProducerManager manager, String topic, String key, String value) {
            this.manager = manager;
            this.topic = topic;
            this.key = key;
            this.value = value;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Producer producer = manager.getProducer();
            for(int i =1 ;i<=1000 ;i++){
                producer.produce(topic, key+"_"+i, value);
            }
            manager.returnProducer(producer);
            log.warn("send msgs cost : key = {} ，cost = {}ms", key, System.currentTimeMillis() - start);
        }
    }

    public static void main(String[] args) {
        KafkaProcessor processor = new KafkaProcessor();
        for (int i = 0; i < 2000; i++) {
            processor.process("test", "key_" + i, "value_" + i);
        }
    }
}
