package wz.test.springmvc.processor.manager;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;
import wz.test.springmvc.service.TestConsumerService;
import wz.test.springmvc.util.LoadConfigUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class KafkaConsumerManager {

    static final String CONFIG_FILE_NAME = "kafka_consumer.properties";
    // key=topic
    ConcurrentHashMap<String, ConsumerTask> consumers = new ConcurrentHashMap<>();

    Properties kafkaConsumerConfig;

    @PostConstruct
    public void init() {
        try {
            kafkaConsumerConfig = LoadConfigUtil.getConfig(CONFIG_FILE_NAME);
            log.info(" ========  load kafka consumer config. properties = {}", kafkaConsumerConfig);
        } catch (IOException e) {
            //加载配置出错。 打印日志，关闭JVM
            log.error("load kafka consumer config error ,system exist! ", e);
            System.exit(1);
        }
    }

    public synchronized void startConsumerTask(String topic) {
        ConsumerTask task = consumers.get(topic);
        if (task != null && task.hasStarted()) {
            log.info("the kafka consumer has started! topic = {}");
            return;
        } else if (task != null && task.hasStop()) {
            closeOld(task);
        }

        task = new ConsumerTask(topic, kafkaConsumerConfig);
        new Thread(task).start();
        consumers.put(topic, task);
    }

    private void closeOld(ConsumerTask task) {
        task.close();
    }

    @Getter
    @Setter
    static class ConsumerTask implements Runnable {
        volatile boolean onServer;
        final String topic;
        Properties kafkaConsumerConfig;
        KafkaConsumer<String, String> consumer;

        public ConsumerTask(String topic, Properties kafkaConsumerConfig) {
            this.topic = topic;
            this.kafkaConsumerConfig = kafkaConsumerConfig;
        }

        @Override
        public void run() {
            setStartFlag();
            consumer = new KafkaConsumer<>(kafkaConsumerConfig);
            log.info("start kafka index  consumer task . topic={}", topic);
            while (onServer) {
                try {
                    consumer.subscribe(Arrays.asList(topic));
                    ConsumerRecords<String, String> records = consumer.poll(1000);
                    Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
                    while (iterator.hasNext()) {
                        //todo service
                        TestConsumerService.consume(iterator.next());
                    }

                } catch (Exception e) {

                } finally {

                }
            }
        }

        public boolean hasStarted() {
            return onServer;
        }

        public boolean hasStop() {
            return !onServer;
        }

        public void setStartFlag() {
            onServer = true;
        }

        public void close() {
            try {
                onServer = false;
                if (consumer != null) {
                    consumer.close();
                }
                log.info("close old consumer task. topic = {}", topic);
            } catch (Exception e) {
                log.error("consumer close error !topic ={},", topic, e);
            } finally {
                consumer = null;
            }
        }
    }

}
