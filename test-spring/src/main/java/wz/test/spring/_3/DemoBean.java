package wz.test.spring._3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by wangz on 17-10-16.
 */
public class DemoBean {
    @Getter
    @Setter
    private String content;

    public DemoBean(String content){
        this.content = content;
    }
}
