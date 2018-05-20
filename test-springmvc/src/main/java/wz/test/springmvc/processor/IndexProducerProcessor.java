package wz.test.springmvc.processor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wz.test.springmvc.constants.IndexActionType;
import wz.test.springmvc.processor.bean.KafkaIndexProducer;
import wz.test.springmvc.processor.manager.KafkaProducerManager;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class IndexProducerProcessor {

    @Autowired
    KafkaProducerManager manager;

    ThreadPoolExecutor processExecutor;

    @PostConstruct
    public void init() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("KafkaProcessor-%d")
                .setDaemon(true)
                .build();
        processExecutor = new ThreadPoolExecutor(50, 50, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), threadFactory);
    }

    public void process(String appId, String topic, IndexActionType actionType, JSONObject data) {
        //todo 处理数据
        processExecutor.execute(new ProcessorTask(manager, appId, topic, data));
    }

    private Callback defaultCallback(String appId, String topic, IndexActionType actionType, JSONObject data) {
        return (RecordMetadata metadata, Exception exception) -> {
            if (metadata != null && metadata.hasOffset()) {
                log.debug("send msg success : topic = {}, key = {}, value = {}, partition = {} , offset= {}, ",
                        topic, data.get("key"), data, metadata.partition(), metadata.offset());
            } else {
                log.error("send msg error : topic = {}， key ={} ,value = {}, ", topic, data.get("key"), data, exception);
            }
        };
    }

    static class ProcessorTask implements Runnable {
        KafkaProducerManager manager;
        String appId;
        String topic;
        JSONObject data;

        public ProcessorTask(KafkaProducerManager manager, String appId, String topic, JSONObject data) {
            this.manager = manager;
            this.appId = appId;
            this.topic = topic;
            this.data = data;
        }

        @Override
        public void run() {
            KafkaIndexProducer producer = manager.getProducer();
            //todo 处理data
//        producer.produce(topic, null, null, null, defaultCallback());
            producer.produce(topic, null, null);
            manager.returnProducer(producer);
        }
    }
}
