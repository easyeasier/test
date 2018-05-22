package wz.test.springmvc.util;

import java.io.*;
import java.util.Properties;

public class LoadConfigUtil {
    static String basePath;

    static {
        basePath = LoadConfigUtil.class.getResource("/conf/").getPath();
    }

    public static Properties getConfig(String fileName) throws IOException {
        String fullPath = basePath + "/" + fileName;

        InputStream in = new BufferedInputStream(new FileInputStream(
                new File(fullPath)));
        Properties prop = new Properties();
        prop.load(in);

        return prop;
    }

    public static void main(String[] args) throws IOException {
        Properties config = LoadConfigUtil.getConfig("kafka_producer.properties");
        System.out.println(config.get("servers"));
    }
}
