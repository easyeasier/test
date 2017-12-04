package wz.test.jdk.sysn;


/**
 * Created by wangz on 17-11-26.
 */

/**
 * 线程获得锁后,sleep测试是否放弃锁
 * 结论:
 * 在sleep的时候是不放弃锁的
 */
public class LockThenSleepTest {

    public LockThenSleepTest() {
    }

    public void lockThenSleep() {
        synchronized (this) {
            System.out.println("lockThenSleep() : currentThread = " + Thread.currentThread().getName() + "获得锁");
            try {
                //分别使用sleep和wait观察输出
//                Thread.sleep(10000);
//                this.wait();  //必须在获得对象锁的情况下,才能调用对象的wait,否则IllegalMonitorStateException
                Thread.currentThread().interrupt();
                System.out.println("after interrupt...");
                // 这里synchronized就是获得的this的锁,其他对象则不行
//            } catch (InterruptedException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void tryLock() {
        long start = System.currentTimeMillis();
        synchronized (this) {
            long period = System.currentTimeMillis() - start;
            if (period > 3000) {
                System.out.println("当前情况没有NOT GET获得对象锁");
            } else {
                System.out.println("当前情况GET获得对象锁");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockThenSleepTest test = new LockThenSleepTest();

        Thread t1 = new Thread(() -> {
            test.lockThenSleep();
        });

        Thread t2 = new Thread(() -> {
            test.tryLock();
        });

        t1.start();
//        t1.join();  //小插曲,t1加塞,要求等待t1执行完 ; yield表示自己可以让行,具体让不让看系统
        t2.start();
    }
}
