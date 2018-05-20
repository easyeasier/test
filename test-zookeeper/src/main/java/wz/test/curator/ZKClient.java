package wz.test.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZKClient {
    static CuratorFramework zkClient = null;
    static String zkServer = "192.168.198.142:2185,192.168.198.142:2186,192.168.198.142:2187";

    public CuratorFramework getZkClient() {
        if (isClosed()) {
            initZKClient();
        }

        return zkClient;
    }

    /**
     * zk连接，当网络抖动时会自动重连
     */
    public synchronized void initZKClient() {
        if (isClosed()) {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            zkClient = CuratorFrameworkFactory.builder()
                    .connectString(zkServer)
                    .retryPolicy(retryPolicy)
                    .sessionTimeoutMs(6000)
                    .connectionTimeoutMs(3000)
                    .build();
            zkClient.start();
        }
    }

    public boolean isClosed() {
        return zkClient == null || zkClient.getState() == CuratorFrameworkState.STOPPED;
    }
}
