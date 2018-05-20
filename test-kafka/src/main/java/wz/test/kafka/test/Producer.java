package wz.test.kafka.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import wz.test.kafka.test.config.KafkaProducerConfig;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Producer {
    static KafkaProducer<String, String> producer;
    static AtomicInteger value = new AtomicInteger(0);

    static {
        producer = new KafkaProducer<String, String>(new KafkaProducerConfig().getConfig());
    }

    public static void main(String[] args) {

        String topic = "test";
        ThreadFactory producerFactory = new ThreadFactoryBuilder()
                .setNameFormat("prodcucers-%d").setDaemon(false).build();
        ThreadPoolExecutor prodcucers = new ThreadPoolExecutor(2, 2, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10),
                producerFactory);

        for (int i = 0; i < 1; i++) {
            Task task = new Task(topic, i + 1);
            prodcucers.submit(task);
        }
    }

    public static class Task implements Runnable {
        String topic;
        int id;

        public Task(String topic, int id) {
            this.topic = topic;
            this.id = id;
        }

        @Override
        public void run() {
            int partitionCount = producer.partitionsFor(topic).size();
            log.warn("======= topic = {}， partCount = {}", topic, partitionCount);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, value.getAndIncrement() + "",
                        value.get() + "");
                Future<RecordMetadata> future = producer.send(record

                        , (metadata, exception) -> {
                            if (metadata != null) {
                                System.out.println("partition = " + metadata.partition() + " " + metadata.hasOffset());
                            } else if (exception != null) {
                                exception.printStackTrace();
                            }
                            System.out.println(record.toString());
                        }
                );
//                log.warn("{} send msg : record = {}", Thread.currentThread().getName(), record);
//                try {
//                    System.out.println(future.get().toString());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
