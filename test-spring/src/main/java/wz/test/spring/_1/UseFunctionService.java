package wz.test.spring._1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by wangz on 17-10-16.
 */
@Service
public class UseFunctionService {
    @Autowired
    FunctionService functionService;


    public String sayHello(String name) {
        return functionService.sayHello(name);
    }

    @Scheduled(cron = "0 * * * * *")
    public void schedule() {
        System.out.println("定时执行。。" + System.currentTimeMillis());
    }
}
