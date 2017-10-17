package wz.test.springmvc._1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangz on 17-10-17.
 */
@Controller
public class InterceptorController {
    @RequestMapping("ic")
    @ResponseBody
    public String interceptor(HttpServletRequest request) {
        Long startTime = (Long) request.getAttribute("startTime");
        if (startTime == null) {
            return "failed";
        } else {
            return "success";
        }
    }
}
