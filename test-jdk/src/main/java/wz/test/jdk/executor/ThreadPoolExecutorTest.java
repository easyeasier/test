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
    public static void main(String[] args) {
        callerRunsPolicyRejectHandlerTest();
    }

    public void test1() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("wz.test.jdk.DescribeVerify-%d").setDaemon(true).build();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 10, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50), threadFactory);
    }

    public static void callerRunsPolicyRejectHandlerTest() {
        long start = System.currentTimeMillis();
//        final int threadCount = 100;
        final int threadCount = 1000;
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Thread-%d").setDaemon(false).build();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20),threadFactory,
//                new ThreadPoolExecutor.AbortPolicy());
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= threadCount; i++) {
            pool.submit(new Task(i + ""));
            System.out.println("submit task-" + i);
        }

        long end = System.currentTimeMillis();
        System.out.println("down..." + (end-start)/1000);
    }

    public static class Task implements Runnable {
        final int loop = 2;
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int i = 0;
            System.out.println(Thread.currentThread().getName() +
                    " task-" + name + " start ..." + "isDemo = " + Thread.currentThread().isDaemon());
            while (i < loop) {
                try {
                    if("main".equals(Thread.currentThread().getName())){
//                        Thread.sleep(30000);
                        Thread.sleep(1500);
                    }else{
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                i++;
            }

            System.out.println(Thread.currentThread().getName() + " task-" + name + " end ...");
        }
    }
}

