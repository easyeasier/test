package wz.test.kafka.test.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class KafkaConfig {
    static Map<String,String> baseConfig = null;

    static {
        baseConfig = new HashMap<>();
    }

    public KafkaConfig() {
    }

    public abstract Properties getConfig();
}
