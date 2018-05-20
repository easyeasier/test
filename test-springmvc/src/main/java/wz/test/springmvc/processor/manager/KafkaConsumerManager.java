package wz.test.springmvc.processor.manager;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;
import wz.test.springmvc.util.LoadConfigUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KafkaConsumerManager {

    static final String CONFIG_FILE_NAME = "kafka_consumer.properties";
    // key=topic
    Map<String, ConsumerTask> consumers = new ConcurrentHashMap<>();

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

    public synchronized void startConsumerTasks(Set<String> topics) {
        Set<String> unStartedServers = topics.stream().filter(topic -> !consumers.containsKey(topic) || !consumers.get
                (topic).isOnServer()).collect(Collectors.toSet());
        List<ConsumerTask> tasks = unStartedServers.stream().map(topic -> new ConsumerTask(topic, kafkaConsumerConfig))
                .collect(Collectors.toList());

        for (ConsumerTask task : tasks) {
            try {
                new Thread(task).start();
                consumers.put(task.getTopic(), task);
            } catch (Exception e) {
                log.error("start consumer task failed . topic = {}", task.getTopic());
            }
        }
    }

    @Getter
    @Setter
    static class ConsumerTask extends Thread {
        volatile boolean onServer;
        final String topic;
        KafkaConsumer<String, String> consumer;

        public ConsumerTask(String topic, Properties kafkaConsumerConfig) {
            this.topic = topic;
            this.consumer = new KafkaConsumer<>(kafkaConsumerConfig);
        }

        @Override
        public void run() {
            onServer = true;
            log.info("start kafka index  consumer task . topic={}", topic);
            while (onServer) {

                try {
                    ConsumerRecords<String, String> records = consumer.poll(1000);
                    Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
                    while (iterator.hasNext()) {
                        //todo index service
                    }

                } catch (Exception e) {

                } finally {

                }
            }
        }

        public boolean isOnServer() {
            return onServer;
        }

        public void close() {
            onServer = false;
            consumer.close();
        }
    }
}
