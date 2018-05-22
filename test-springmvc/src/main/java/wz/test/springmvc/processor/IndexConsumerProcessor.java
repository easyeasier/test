package wz.test.springmvc.processor;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wz.test.springmvc.processor.manager.KafkaConsumerManager;
import wz.test.springmvc.util.LoadConfigUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Slf4j
@Service
public class IndexConsumerProcessor {

    static final String ZK_CONFIG_NAME = "zookeeper.properties";
    static final String ZK_TOPIC_PATH = "/configs";
    @Autowired
    KafkaConsumerManager indexConsumerManager;
    CuratorFramework zkClient;
    volatile Set<String> topics = Sets.newConcurrentHashSet();

    @PostConstruct
    public void init() {
        String zkServers = null;
        try {
            Properties config = LoadConfigUtil.getConfig(ZK_CONFIG_NAME);
            log.info(" ========  load kafka consumer config. properties = {}", config);
            zkServers = (String) config.get("servers");

            initZKClient(zkServers);
            addPathEvent(ZK_TOPIC_PATH);
        } catch (IOException e) {
            //加载配置出错。 打印日志，关闭JVM
            log.error("load zookeeper config error ,system exist! ", e);
            System.exit(1);
        } catch (Exception e) {
            //加载配置出错。 打印日志，关闭JVM
            log.error("zookeeper init error. system exist! plz check! ", e);
            System.exit(1);
        }
    }

    public Set<String> getAllTopics() {
        return topics;
    }

    public boolean containTopic(String topic) {
//        if (topics == null) {
//            try {
//                topics = new HashSet<>();
//                List<String> children = getZKChildren(ZK_TOPIC_PATH);
//                topics.addAll(children);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        return topics == null ? false : topics.contains(topic);
    }

    private void addPathEvent(String path) throws Exception {
        PathChildrenCache watcher = new PathChildrenCache(zkClient, path, true);
        watcher.getListenable().addListener((client, event) -> {
            try {
                if (event.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED) {
                    String topicPath = event.getData().getPath();
                    String topic = splitTopic(topicPath);
                    log.info("topic has changed. changeType = {}, topic_path = {}, topic={}", event.getType(),
                            topicPath, topic);
                    indexConsumerManager.startConsumerTask(topic);
                    topics.add(topic);
                }
            } catch (Exception e) {
                log.error("zk node changed ,but process error. node = {}, ", event.getData(), e);
            }
        });
        watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
    }

    private List<String> getZKChildren(String path) throws Exception {
        return zkClient.getChildren().forPath(path);
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

    private String splitTopic(String path) {
        return path.substring(path.lastIndexOf("/")+1);
    }


}
