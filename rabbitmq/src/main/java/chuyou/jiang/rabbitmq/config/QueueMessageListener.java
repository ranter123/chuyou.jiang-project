package chuyou.jiang.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2020/8/16 12:54 上午
 * @Description:
 */
@Component
public class QueueMessageListener {

    final Logger logger = LoggerFactory.getLogger(getClass());

    static final String QUEUE_MAIL = "q_mail";

    static final String QUEUE_SMS = "q_sms";

    static final String QUEUE_APP = "q_app";
//
//    @RabbitListener(queues = QUEUE_MAIL)
//    public void onRegistrationMessageForMailQueue()
}
