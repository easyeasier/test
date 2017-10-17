package wz.test.springmvc._1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import wz.test.springmvc._1.model.DemoObj;

/**
 * Created by wangz on 17-10-17.
 */
@Controller
public class ExceptionHandler {
    /**
     * 1.异常会被捕获
     * 2.ModelAttribute从全局变量取msg
     * 3.DemoObj中id参数会被重置,详见ExceptionHandler
     * @param msg
     * @param obj
     * @return
     */
    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj) {
        throw new IllegalArgumentException("非常抱歉,参数有误,来自 @ModelAttributes : " + msg + ", obj = " + obj.toString());
    }
}
