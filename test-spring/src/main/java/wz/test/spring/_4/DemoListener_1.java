package wz.test.spring._4;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by wangz on 17-10-16.
 */
@Component
public class DemoListener_1 implements ApplicationListener<DemoEvent_1> {
    @Override
    public void onApplicationEvent(DemoEvent_1 demoEvent) {
        String msg = demoEvent.getMsg();
        System.out.println("this is listner_1. the msg is : " + msg);
    }
}
