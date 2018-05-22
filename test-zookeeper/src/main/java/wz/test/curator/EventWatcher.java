package wz.test.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

@Slf4j
public class EventWatcher {
    CuratorFramework zkClient = null;

    public EventWatcher(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    /**
     * 添加path上的监控， 监控子节点的变化
     *
     * @param path
     * @throws Exception
     */
    public void addPathEvent(String path) throws Exception {
        PathChildrenCache watcher = new PathChildrenCache(zkClient, path, true);
        watcher.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("thread = " + Thread.currentThread().getName() + " type = " + event.getType() +
                                ", data = " +event.getData()+ "event.getData() + , " +
                        "dataList = " + event.getInitialData());

                Thread.sleep(2000);
            }
        });

        watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
    }
}
