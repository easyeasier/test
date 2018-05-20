package wz.test.kafka.test.config;


import java.util.Properties;

public class KafkaConsumerConfig extends KafkaConfig {
    Properties consumerConfig = null;

    public KafkaConsumerConfig() {
        initConfig();
    }

    public void initConfig() {
        consumerConfig = new Properties();
        consumerConfig.putAll(KafkaConfig.baseConfig);

        consumerConfig.put("bootstrap.servers", "192.168.198.128:9092");
        // value的反序lie化
        consumerConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 消费者的分组，为了避免重复消费，一定要保证在一组中
        consumerConfig.put("group.id", "wz");

        //一起请求拉取的最小字节数，如果为1的话就是只要有拉取请求
        consumerConfig.put("fetch.min.bytes", 1);

        //心跳的时间，通过心跳判断consumer示例还存不存在，如果不存在则会rebalance consumers(不大于session.timeout.ms,通常不大于它的1/3)
        consumerConfig.put("heartbeat.interval.ms", 3000);

        //一个partition能返回的最大字节数
        consumerConfig.put("max.partition.fetch.bytes", 1048576);


        consumerConfig.put("session.timeout.ms", 10000);

        //offset不存在时 ，reset offset值的位置。  latest：最新，none:offset没有的时候抛出exception
        consumerConfig.put("auto.offset.reset", "earliest");

        //最大的空闲时间，超过就关闭
        consumerConfig.put("connections.max.idle.ms", 540000);

        //是否自动consumer提交offset
        consumerConfig.put("enable.auto.commit", true);

        //一次poll最大的records
        consumerConfig.put("max.poll.records", 500);

        //连接失败后等待的时间
        consumerConfig.put("reconnect.backoff.ms", 50);

        //连接失败多次之后最大的等待时间 todo 当前版本不支持
        consumerConfig.put("reconnect.backoff.max.ms", 1000);
        
        // 是否自动提交offset
        consumerConfig.put("enable.auto.commit", true);

        // 自动提交的时间间隔
        consumerConfig.put("auto.commit.interval.ms", 1000);

        consumerConfig.put("request.timeout.ms", 400000);
    }

    @Override
    public Properties getConfig() {
        return consumerConfig;
    }

    public static void main(String[] args) {
        System.out.println(new KafkaConsumerConfig().getConfig());
    }
}
