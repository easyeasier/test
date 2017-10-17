package wz.test.springmvc._1.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wangz on 17-10-16.
 */
@Controller
//@RestController   等价于 @Controller和ResponseBody
public class HelloController {
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "json1")
    @ResponseBody
    public String jsonFun() {
        Map<String, String> data = Maps.newHashMap();
        data.put("key", "value");

        return data.toString();
    }

    @RequestMapping("json2")
    public Map<String, String> jsonFun2() {
        Map<String, String> data = Maps.newHashMap();
        data.put("key", "value");

        return data;
    }
}
