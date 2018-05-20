package wz.test.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("/source/{value}")
    @ResponseBody
    public String returnStr(@PathVariable(name = "value") String value) {
        return "back_" + value;
    }

    @ResponseBody
    @RequestMapping("/source2/{value}/{value2}")
    public String returnStr2(@PathVariable String value,@PathVariable String value2) {
        return "back_" + value+"_"+value2;
    }

    @ResponseBody
    @RequestMapping("/error")
    public void returnError(String value) {
        throw new RuntimeException("eeeeeror");
    }
}
