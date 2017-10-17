package wz.test.spring._2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wangz on 17-10-16.
 */

/**
 * 使用java配置初始化bean
 *
 * 1. 使用service上不再使用@Service注解
 * 2. 在配置类中,设置service名称的方法,并用@Bean注解在方法上
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

        UseFunctionService service = context.getBean(UseFunctionService.class);
        System.out.println(service.sayHello("wz"));
    }


}
