package wz.test.spring._1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wangz on 17-10-16.
 */

/**
 * 注解类代替xml注入bean
 * 1.Bean类需要 @Service @Component等注解
 * 2.需要config类,@Configuration 相当于xml注解,@ComponentScan 扫描相关包
 * 3.需要用AnnotationConfigApplicationContext(config.class)
 */
public class Main {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);

        UseFunctionService service = context.getBean(UseFunctionService.class);

        System.out.println(service.sayHello("wz"));
        context.close();
    }
}
