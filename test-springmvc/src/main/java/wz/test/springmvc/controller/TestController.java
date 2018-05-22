package wz.test.springmvc.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wz.test.springmvc.constants.IndexActionType;
import wz.test.springmvc.processor.IndexConsumerProcessor;
import wz.test.springmvc.processor.IndexProducerProcessor;

@Controller
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    IndexProducerProcessor producerProcessor;

    @Autowired
    IndexConsumerProcessor consumerProcessor;

    @RequestMapping(value = "/{appId}/{topic}", method = RequestMethod.POST)
    @ResponseBody
    public String test(
            @PathVariable(name = "appId") String appId,
            @PathVariable(name = "topic") String topic,
            @RequestBody Data data) {
        log.info("controller : topic ={}, appId={}, data ={}", topic, appId, data.toString());
        if(consumerProcessor.containTopic(topic)){
            producerProcessor.process(appId, topic, IndexActionType.add, JSON.parseObject(JSON.toJSONString(data)));
            return "success";
        }

        log.error("not contain topic = {}, plz CHECK" ,topic);
        return "error";
    }

    @lombok.Data
    public static class Data {
        String key;
        String value;

        @Override
        public String toString() {
            return "key = " + key + "value = " + value;
        }
    }
}
