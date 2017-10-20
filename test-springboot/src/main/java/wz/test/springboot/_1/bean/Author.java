package wz.test.springboot._1.bean;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wangz on 17-10-19.
 */
@Component
@ConfigurationProperties(prefix = "book")
public class Author {
    @Getter
    private String name;
    @Getter
    private int age;
}
