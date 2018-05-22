package wz.test.springmvc.processor.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Service;
import wz.test.springmvc.util.LoadConfigUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class KafkaProducerFactory implements PooledObjectFactory<KafkaIndexProducer> {

    private static final String CONFIG_FILE_NAME = "kafka_producer.properties";
    private AtomicInteger currentId = new AtomicInteger(1);
    private Properties producerConfig;

    @PostConstruct
    public void loadConfig() {
        try {
            producerConfig = LoadConfigUtil.getConfig(CONFIG_FILE_NAME);
            log.info(" ========  load kafka producer config. properties = {}", producerConfig);
        } catch (IOException e) {
            //加载配置出错。 打印日志，关闭JVM
            log.error("load kafka producer error ,system exist! ", e);
            System.exit(1);
        }
    }

    @Override
    public PooledObject<KafkaIndexProducer> makeObject() {
        PooledObject<KafkaIndexProducer> producerObject = new DefaultPooledObject<>(new KafkaIndexProducer(currentId
                .getAndIncrement(), producerConfig));

        log.info("make new kafka producer : producer_id = {}", currentId.get());
        return producerObject;
    }

    @Override
    public void destroyObject(PooledObject<KafkaIndexProducer> producerObject) {
        KafkaIndexProducer producer = producerObject.getObject();
        producer.close();
        log.info("destroy kafka index producer : producer_id = {}", producer.getId());
        producer = null;
    }

    @Override
    public boolean validateObject(PooledObject<KafkaIndexProducer> p) {
        return true;
    }

    @Override
    public void activateObject(PooledObject<KafkaIndexProducer> p) {
    }

    @Override
    public void passivateObject(PooledObject<KafkaIndexProducer> p) {
    }
}
