package wz.test.kafka.manager;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import wz.test.kafka.manager.config.KafkaProducerConfig;
import wz.test.kafka.manager.producer.Producer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaProducerFactory implements PooledObjectFactory<Producer> {

    private KafkaProducerConfig config;
    AtomicInteger num = new AtomicInteger(1);

    public KafkaProducerFactory(KafkaProducerConfig config) {
        this.config = config;
    }

    @Override
    public PooledObject<Producer> makeObject()  {
        return new DefaultPooledObject<>(new Producer(num.getAndIncrement(), config));
    }

    @Override
    public void destroyObject(PooledObject<Producer> p) {
        Producer producer = p.getObject();
        producer.close();
        producer = null;
    }

    @Override
    public boolean validateObject(PooledObject<Producer> p) {
        return true;
    }

    @Override
    public void activateObject(PooledObject<Producer> p) {

    }

    @Override
    public void passivateObject(PooledObject<Producer> p) {

    }


    public static class PartitionChooser implements Partitioner {
        @Override
        public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
            return cluster.partitionCountForTopic(topic) == null ? 0 : Math.abs(key.hashCode()) % cluster.partitionCountForTopic(topic);
        }

        @Override
        public void close() {

        }

        @Override
        public void configure(Map<String, ?> configs) {

        }

        public static void main(String[] args) {
            System.out.println(new PartitionChooser());
            System.out.println(5 % 2);
        }
    }
}
