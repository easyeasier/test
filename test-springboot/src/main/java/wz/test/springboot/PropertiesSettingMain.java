package wz.test.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wz.test.springboot.bean.Author;

/**
 * Created by wangz on 17-10-19.
 */
@SpringBootApplication
@RestController
public class PropertiesSettingMain {

    @Autowired
    Author author;

    @RequestMapping("/")
    public String customProperties() {
        return author.getName() + " : " + author.getAge();
    }

    public static void main(String[] args) {
        SpringApplication.run(PropertiesSettingMain.class, args);
    }
}
