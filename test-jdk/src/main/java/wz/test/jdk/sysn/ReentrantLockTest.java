package wz.test.jdk.sysn;

/**
 * Created by wangz on 17-11-27.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 主要测试lock和lockInterruptibly的区别
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws Exception {
//        test1();
        test2();
    }

    /**
     * lock的情况下, 子线程t1将永远被阻塞
     * @throws Exception
     */
    public static void test1() throws Exception{
        final Lock lock=new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1=new Thread(new Runnable(){
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" interrupted.");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000000);
    }

    /**
     * 当lock获取不到的
     * @throws Exception
     */
    public static void test2() throws Exception{
        final Lock lock=new ReentrantLock();
        lock.lock();
//        Thread.sleep(1000);
        Thread t1=new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println("print");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" interrupted.");
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000000);
    }
}
