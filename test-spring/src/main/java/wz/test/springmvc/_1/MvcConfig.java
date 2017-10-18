package wz.test.springmvc._1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import wz.test.springmvc._1.interceptor.DemoInterceptor;

/**
 * Created by wangz on 17-10-16.
 */

/**
 * 继承 WebMvcConfigurerAdapter 的目的,是可以对spring MVC配置
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("wz.test.springmvc")
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);

        return viewResolver;
    }

    /**
     * 添加静态资源映射
     * 可通过
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("assets/**")   //指文件放置的目录
                .addResourceLocations("classpath:/assets/"); //指对外暴露的访问路径
    }

    /**
     * 添加拦截器,
     * 拦截器需继承HandlerInterceptorAdapter
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }

    /**
     * 简单的页面跳转controller,可替代Controller的index跳转的作用
     * path : localhost:8080/toUpload
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/async").setViewName("async");
    }

    /**
     * 文件类型解析器,上传文件时需要
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

    private DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }
}
