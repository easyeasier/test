package wz.test.springmvc._1.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangz on 17-10-17.
 */

/**
 * 全局控制器
 * ControllerAdvice 内组合了@Component,所以其实也是有一个bean
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 全局处理器
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception) {
//    public ModelAndView exception(Exception exception, WebRequest request) { //与上面没什么分别
        ModelAndView modelAndView = new ModelAndView("error"); // error页面
        modelAndView.addObject("errorMessage", exception.getMessage());

        return modelAndView;
    }

    /**
     * 全局设置变量. 在Controller方法的参数中,可通过ModelAttribute获取全局参数
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "额外信息");
    }

    /**
     * 定制WebDataBinder 此处是将id置为无效
     *
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }
}
