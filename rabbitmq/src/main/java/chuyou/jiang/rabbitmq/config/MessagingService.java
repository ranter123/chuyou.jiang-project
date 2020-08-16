package chuyou.jiang.rabbitmq.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2020/8/16 12:49 上午
 * @Description:
 */
@Component
public class MessagingService {

//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    public void sendRegistrationMessage(RegistrationMessage msg) {
//        rabbitTemplate.convertAndSend("registration", "", msg);
//    }
//
//    public void sendLoginMessage(LoginMessage msg) {
//
//    }
}
