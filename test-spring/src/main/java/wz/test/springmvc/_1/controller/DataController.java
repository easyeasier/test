package wz.test.springmvc._1.controller;

/**
 * Created by wangz on 17-10-17.
 */

import org.springframework.web.bind.annotation.*;
import wz.test.springmvc._1.model.DemoObj;

import javax.servlet.http.HttpServletRequest;

/**
 * 只返回数据的交互,而不是html文档
 * 1.@RestController = @Controller + @ResponseBody
 */

@RestController
@RequestMapping("/data")
public class DataController {

    @RequestMapping(value = "1/{str}", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String demoPathVar(@PathVariable String str, HttpServletRequest request) {
        return "url : " + request.getRequestURL() + "can aceess, str : " + str;
    }

    @RequestMapping(value = "2", produces = "text/plain;charset=utf-8")
    public String queryParam(int id, HttpServletRequest request) {
        return "url : " + request.getRequestURL() + "can aceess, id : " + id;
    }

    /**
     * 可接受 Get请求,query param上的参数  和 Post 请求, x-www-form-urlencoded请求
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping(value = "3", produces = "text/plain;charset=utf-8")
    public String queryParamBody(DemoObj obj, HttpServletRequest request) {
        return "url : " + request.getRequestURL() + "can aceess, id : " + obj.getId() + ", name : " + obj.getName();
    }


    /**
     * 接受 POST请求,json格式的参数
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping(value = "4", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String requestBodyParam(@RequestBody DemoObj obj, HttpServletRequest request) {
        return "url : " + request.getRequestURL() + "can aceess, id : " + obj.getId() + ", name : " + obj.getName();
    }

    /**
     * 需要处理json的jar包
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "5", produces = "application/json;charset=utf-8")
    public DemoObj jsonResponse(HttpServletRequest request) {
        DemoObj obj = new DemoObj();
        obj.setId(4);
        obj.setName("wz");
        return obj;
    }

}
