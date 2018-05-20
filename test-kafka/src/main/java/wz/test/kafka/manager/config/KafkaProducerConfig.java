package wz.test.kafka.manager.config;

import java.util.Properties;

public class KafkaProducerConfig extends KafkaConfig {
    Properties producerConfig = null;

    public KafkaProducerConfig() {
        initConfig();
    }

    @Override
    public Properties getConfig() {
        return producerConfig;
    }


    public void initConfig() {
        producerConfig = new Properties();
        producerConfig.putAll(KafkaConfig.baseConfig);

        //1.server地址， 集群环境会自动发现其他的server； 多个服务，逗号隔开
        producerConfig.put("bootstrap.servers", "192.168.198.128:9092");
        //2. 序列化实现类 （consumer要反序列化）
        producerConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /**
         *   3 . 发送确认状态
         *    0 - 不保证发送成功，此时retries属性也不会生效。返回的offset是始终是-1
         *    1 - leader成功时确认，但不保证从同步成功
         *    -1，all （相同效果）不会保证数据丢失，在从和主都存储时，返回状态
         */
        producerConfig.put("acks", "1");

        //压缩类型 默认none， 其他可选值gzip，snappy，lz4
        producerConfig.put("compression.type", "none");

        //失败重发次数 0-2147483647 ；如果max.in.flight.requests.per.connection !=1 可能导致顺序错乱 /默认0
        producerConfig.put("retries", 3);

        //producer buffer大小
        producerConfig.put("buffer.memory", 32 * 1024 * 1024);

        //一次请求的批量大小，是根据partition来batch的，默认16384 （16*1024）
        producerConfig.put("batch.size", 1000);

        //一次请求最大的record 批量数 默认1M
        producerConfig.put("max.request.size", 1048576);

        //producer client id. 可用于追踪请求来源
//        producerConfig.put("client.id", "producer1");

        //todo 空闲多少ms时关闭连接。 默认9分钟；关闭后能不能够再连接使用？
        producerConfig.put("connections.max.idle.ms", 54000);

        //默认为0 发送不延迟。  默认情况下，到达batch.size后，即发往broker，但可以延迟一定时间，再组合后面的数据，以提高吞吐量
        //一般在record生产比发送快的情况下使用
        producerConfig.put("linger.ms", 0);

        //send（）或partitionsFor()的最大阻塞时间，可能是buffer full 默认1分钟
        producerConfig.put("max.block.ms", 60000);

        //自定义选择topic的partition。 需要实现org.apache.kafka.clients.producer.Partitioner接口 todo
//        producerConfig.put("partitioner.class", null);

        //tcp接收/发送的buffer大小， 如果-1，将采用系统默认的大小
        producerConfig.put("receive.buffer.bytes", -1);
        producerConfig.put("send.buffer.bytes", -1);

        //接受ack请求的默认超时时长；如果超时，认为失败，重传
        producerConfig.put("request.timeout.ms", 60000);

        //true保持幂等性。每次写入或重试写入，都会将每个msg写到每个副本的stram中。true的话，retries必须大于0，acks必须为all
        producerConfig.put("enable.idempotence", false);

        //消息发送到kafka集群时，要走的拦截器列表。需要实现org.apache.kafka.clients.producer.ProducerInterceptor接口
//        producerConfig.put("interceptor.classes", null);

        //单个连接发送的最大请求数（未确认的） 默认5
        producerConfig.put("max.in.flight.requests.per.connection", 1);

        //partition元数据更新的周期 ，默认300000ms（5m）
        producerConfig.put("metadata.max.age.ms", 300 - 000);

        //一次连接失败后，等待下次重连的时间，多次失败后以指数上升，直到下面的值
        producerConfig.put("reconnect.backoff.ms", 1000);

        //在多次连接失败后，wait的最大等待时间
        producerConfig.put("reconnect.backoff.max.ms", 1000);

        //失败重发的等待时间
        producerConfig.put("retry.backoff.ms", 100);

        //加密认证,默认null
//        producerConfig.put("ssl.key.password", null);
        //下面两项配合使用， 文件位置和密码， 默认为null
//        producerConfig.put("ssl.keystore.location", null);
//        producerConfig.put("ssl.keystore.password", null);
        //下面两个也是和ssl相关的， 先不看了
//        producerConfig.put("ssl.truststore.location", null);
//        producerConfig.put("ssl.truststore.password", null);

        /**
         * 其他
         */
        //发送消息异步发送还是同步 默认是同步sync
        producerConfig.put("producer.type", "sync");
    }

    public static void main(String[] args) {
        KafkaConfig config = new KafkaProducerConfig();
        System.out.println(config.getConfig());
    }
}
