package wz.test.springboot._1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangz on 17-10-18.
 */

//@Controller
//@SpringBootApplication //组合了下面3个注解. 作用: 1.自动扫描被注解的类及下级子包目录.
//@EnableAutoConfiguration  //自动根据依赖包配置配置项
//@ComponentScan
//@Configuration
public class Main {

    //默认是application.properties配置文件中
    @Value("${book.author}")
    private String author;

    @Value("${book.name}")
    private String name;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return author + ", " + name;
    }

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Main.class, args);  //默认方式
        SpringApplication app = new SpringApplication(Main.class);
        // 可从网站 http://patorjk.com/software/taag 生成banner
        app.setBannerMode(Banner.Mode.OFF); //关闭banner(横幅,标语). 默认在banner.txt中自定义.
        app.run(args);
    }
}
