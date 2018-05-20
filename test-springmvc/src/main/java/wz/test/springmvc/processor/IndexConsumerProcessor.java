package wz.test.springmvc.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wz.test.springmvc.processor.manager.KafkaConsumerManager;
import wz.test.springmvc.util.LoadConfigUtil;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IndexConsumerProcessor {

    static final String ZK_CONFIG_NAME = "zookeeper.properties";
    static final String ZK_TOPIC_PATH = "/configs";
    @Autowired
    KafkaConsumerManager indexConsumerManager;
    CuratorFramework zkClient;

    @PostConstruct
    public void init() {
        String zkServers = null;
        try {
            Properties config = LoadConfigUtil.getConfig(ZK_CONFIG_NAME);
            log.info(" ========  load kafka consumer config. properties = {}", config);
            zkServers = (String) config.get("servers");
        } catch (Exception e) {
            //加载配置出错。 打印日志，关闭JVM
            log.error("load zookeeper config error ,system exist! ", e);
            System.exit(1);
        }

        initZKClient(zkServers);
        addPathEvent(ZK_TOPIC_PATH);
    }

    public Set<String> getAllTopics() {
//        zkClient.getChildren().forPath(ZK_TOPIC_PATH);
        //todo
        return null;
    }

    private void addPathEvent(String path) {
        PathChildrenCache watcher = new PathChildrenCache(zkClient, path, true);
        watcher.getListenable().addListener((client, event) -> {
            Set<String> topics = event.getInitialData().stream().map(node -> getTopicFromZKPath(node.getPath()))
                    .collect(Collectors.toSet());

            log.info("topic has changed. changeType = {}, topics = {}", event.getType(), topics);
            indexConsumerManager.startConsumerTasks(topics);
        });
    }

    private void initZKClient(String zkServers) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(zkServers)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .build();
        zkClient.start();
    }

    private String getTopicFromZKPath(String path) {
        return path.substring(path.lastIndexOf("/"));
    }
}
