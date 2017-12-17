package wz.test.jdk.executor;

import com.google.common.collect.Lists;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wangz on 17-12-4.
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> call1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return Thread.currentThread().getName() + "123";
            }
        };

        Callable<String> call2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return Thread.currentThread().getName() + "123";
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future> list = Lists.newArrayList();
        list.add(executorService.submit(call1));
        list.add(executorService.submit(call2));

        int count = 1;
        while (true) {

            for (int i = 1; i < 3; i++) {
                System.out.println("任务" + i + "的状态是 : " + list.get(i - 1).isDone());
            }
            count++;
            Thread.sleep(1000);
        }
    }
}
