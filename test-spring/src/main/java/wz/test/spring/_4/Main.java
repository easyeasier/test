package wz.test.spring._4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wangz on 17-10-16.
 */

/**
 * c
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
//        context.publishEvent(new DemoEvent_1(Thread.currentThread(), "0101"));
        DemoPublisher publisher = context.getBean(DemoPublisher.class);
        publisher.publish(1,"i am msg_1");
        publisher.publish(2,"i am msg_2");
        context.close();
    }
}
