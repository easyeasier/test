package wz.test.kafka.manager;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import wz.test.kafka.manager.config.KafkaProducerConfig;
import wz.test.kafka.manager.producer.Producer;

@Slf4j
public class KafkaProducerManager {
    GenericObjectPool<Producer> pool;
    KafkaProducerFactory factory;

    public KafkaProducerManager() {
        factory = new KafkaProducerFactory(new KafkaProducerConfig());
        pool = new GenericObjectPool(factory);
    }

    public Producer getProducer() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            log.error("get producer from pool error : ",e);
            return null;
        }
    }

    public void returnProducer(Producer producer) {
        pool.returnObject(producer);
    }
}
