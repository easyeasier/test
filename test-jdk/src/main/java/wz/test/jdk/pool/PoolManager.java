package wz.test.jdk.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;

import java.util.concurrent.*;

public class PoolManager {
    static GenericObjectPool<User> pool;
    static ExecutorService userThreadPool;

    static {
        pool = new GenericObjectPool<>(new UserFactory());
        userThreadPool = new ThreadPoolExecutor(50, 50, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000));
    }

    public void process(Task task) {
        userThreadPool.execute(task);
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            try {
                User user = pool.borrowObject();
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " -- " + user);
                Thread.sleep(2000);
                pool.returnObject(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PoolManager manager = new PoolManager();
        for(int i=0;i <100;i++){
            Task task = new Task();
            manager.process(task);
        }
    }
}
