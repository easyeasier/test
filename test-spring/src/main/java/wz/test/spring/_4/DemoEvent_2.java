package wz.test.spring._4;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by wangz on 17-10-16.
 */
public class DemoEvent_2 extends ApplicationEvent {
    @Getter
    @Setter
    String msg;


    public DemoEvent_2(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
