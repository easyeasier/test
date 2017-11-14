package wz._3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by wangz on 17-10-20.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){ //注册STOMP的协议节点endpoint,并映射到指定的url
        registry.addEndpoint("/endpointWisely").withSockJS();//注册endpoint,并指定使用SockJS协议
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){ //配置消息代理
        registry.enableSimpleBroker("/topic"); //[配置广播/topic消息代理
    }
}
