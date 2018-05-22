package wz.test.springmvc.processor.manager;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wz.test.springmvc.exception.IndexExceptionCode;
import wz.test.springmvc.exception.IndexRuntimeException;
import wz.test.springmvc.processor.bean.KafkaIndexProducer;
import wz.test.springmvc.processor.bean.KafkaProducerFactory;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class KafkaProducerManager {

    GenericObjectPool<KafkaIndexProducer> pool;

    @Autowired
    KafkaProducerFactory producerFactory;

    @PostConstruct
    public void init() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(1);
        pool = new GenericObjectPool(producerFactory, new GenericObjectPoolConfig());
    }

    public KafkaIndexProducer getProducer() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            log.error("get kafka index producer from pool error : ", e);
            throw new IndexRuntimeException(IndexExceptionCode.INDEX_KAFKA_PRODUCER_ERROR);
        }
    }

    public void returnProducer(KafkaIndexProducer producer) {
        pool.returnObject(producer);
    }
}
