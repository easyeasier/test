package wz.test.spring._4;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by wangz on 17-10-16.
 */
@Component
public class DemoListener_2 implements ApplicationListener<DemoEvent_2> {
    @Override
    public void onApplicationEvent(DemoEvent_2 demoEvent_2) {
        System.out.println("this is listner_2. the msg is = " + demoEvent_2.getMsg());
    }
}
