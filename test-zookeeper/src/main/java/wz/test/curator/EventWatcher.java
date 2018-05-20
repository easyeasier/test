package wz.test.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

public class EventWatcher {
    CuratorFramework zkClient = null;

    public EventWatcher(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    /**
     * 添加path上的监控， 监控子节点的变化
     * @param path
     * @throws Exception
     */
    public void addPathEvent(String path) throws Exception {
        PathChildrenCache watcher = new PathChildrenCache(zkClient, path, true);
        watcher.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("type = " + event.getType() + ", data = " + event.getData() + ", " +
                        "dataList = " + event.getInitialData());
            }
        });

        watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
    }
}
