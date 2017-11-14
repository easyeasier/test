package wz._3.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import wz._3.model.WiselyMessage;
import wz._3.model.WiselyResponse;


/**
 * Created by wangz on 17-10-20.
 */
@Controller
public class WsController {

    @MessageMapping("/welcome")  //用于映射WebSocket url,跟RequestMapping相同的效果
    @SendTo("/topic/getResponse")  //向订阅了这个topic的websocket浏览器端群发消息
    public WiselyResponse say(WiselyMessage message) throws InterruptedException {
        Thread.sleep(3000);
        return new WiselyResponse("Welcome " + message.getName() + "!");
    }
}
