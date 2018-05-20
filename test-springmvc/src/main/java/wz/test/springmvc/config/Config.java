package wz.test.springmvc.config;

import java.util.Properties;

public abstract class Config {
    String configName;
    Properties config;

    public Config(String configName) {
        this.configName = configName;
        loadConfig();
    }

    public void loadConfig() {
        config = new Properties();
    }

    public Properties getConfig() {
        return config;
    }
}
