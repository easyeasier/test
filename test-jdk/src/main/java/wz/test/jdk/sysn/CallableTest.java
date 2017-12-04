package wz.test.jdk.sysn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangz on 17-12-4.
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return Thread.currentThread().getName() + "123";
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println(executorService.submit(call).get());
        System.out.println(executorService.submit(call).get());
    }
}
