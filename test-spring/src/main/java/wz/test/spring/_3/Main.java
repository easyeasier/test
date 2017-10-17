package wz.test.spring._3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wangz on 17-10-16.
 */

/**
 * Profiles设置环境变量:
 * 1. 在配置类的bean方法上@Profile声明环境
 * 2.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
// 需必须在setActiveProfiles后注册
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);
        context.getEnvironment().setActiveProfiles("prod");
        context.register(ProfileConfig.class); //必须在必须在setActiveProfiles后
        context.refresh(); //刷新容器,必须的

        DemoBean demoBean = context.getBean(DemoBean.class);
        System.out.println(demoBean.getContent());
    }
}
