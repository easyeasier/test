package wz.test.springboot._1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wz.test.springboot._1.bean.Author;

/**
 * Created by wangz on 17-10-19.
 */
@SpringBootApplication
@RestController
@EnableScheduling
public class PropertiesSettingMain {

    @Autowired
    Author author;

    @RequestMapping("/")
    public String customProperties() {
        return author.getName() + " : " + author.getAge();
    }

    public static void main(String[] args) {
        System.out.println("main : " + Thread.currentThread());
        SpringApplication.run(PropertiesSettingMain.class, args);
    }
}
