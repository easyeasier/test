package wz.test.kafka;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import wz.test.kafka.manager.consume.Consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
@Setter
public class Main {
    final String topic;
    String key;

    public Main(String topic, String key) {
        this.topic = topic;
        this.key = key;
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("test");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread thread2 = new Thread(() -> System.out.println("sss"));
        thread2.start();

        while (true) {
            System.out.println(thread.getState());
            System.out.println(thread2.getState());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
