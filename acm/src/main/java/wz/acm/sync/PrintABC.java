package wz.acm.sync;

/**
 * Created by wangz on 17-11-4.
 */

import java.nio.charset.CodingErrorAction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3个线程循环打印abc
 */
public class PrintABC {


    public static class PrintThread implements Runnable {
        private String symbol;
        private Condition conditionA = null;
        private int go = 0;
        private ReentrantLock lock = null;
        //使用原子类，本例中并没有多大意义
        private static AtomicInteger i = new AtomicInteger(0);

        public PrintThread(String symbol, Condition conditionA, int go, ReentrantLock lock) {
            this.symbol = symbol;
            this.lock = lock;
            this.conditionA = conditionA;
            this.go = go;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (i.get() % 3 != go) {
                        conditionA.await();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //防止过多输出
                if (i.get() == 10) break;
                System.out.println("result " + symbol);
                i.getAndIncrement();
//            System.out.println(i.get());
                //可以试试signalAll或signal区别，比较时建议去掉前面的break
                conditionA.signalAll();
                lock.unlock();

            }
        }
    }

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new PrintThread("A", conditionA, 0, lock)).start();
        new Thread(new PrintThread("B", conditionA, 1, lock)).start();
        new Thread(new PrintThread("C", conditionA, 2, lock)).start();
    }
}
