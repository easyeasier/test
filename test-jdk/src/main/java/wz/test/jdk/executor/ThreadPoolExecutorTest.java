package wz.test.jdk.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangz on 17-11-12.
 */
public class ThreadPoolExecutorTest {
    static ThreadPoolExecutor pool;
    static{
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("wz.test.jdk.DescribeVerify-%d").setDaemon(true).build();
        pool = new ThreadPoolExecutor(5,10,20, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50),threadFactory);
    }
}
