package wz.test.springboot._1.bean;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Scheduled(cron = "0 * * * * *")
    public void task(){
        System.out.println("执行。。。"+System.currentTimeMillis() + ", thread : "+ Thread.currentThread());
    }
}
