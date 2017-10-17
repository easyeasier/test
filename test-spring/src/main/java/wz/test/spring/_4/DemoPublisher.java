package wz.test.spring._4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by wangz on 17-10-16.
 */

/**
 * context本身也是容器中的一个bean
 */
@Service
public class DemoPublisher {
    @Autowired
    ApplicationContext context;

    public void publish(int index, String msg) {
        if (index % 2 == 1) {
            publistEvent_1(msg);
        } else {
            publishEvent_2(msg);
        }
    }

    private void publistEvent_1(String msg) {
        context.publishEvent(new DemoEvent_1(this, msg));
    }

    private void publishEvent_2(String msg) {
        context.publishEvent(new DemoEvent_2(this, msg));
    }
}
