package chuyou.jiang.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ranter
 * @Date: 2020/8/16 2:55 下午
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void sendMsg() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("user_name", "ranter");
        map.put("password", "ranter");
        rabbitTemplate.convertAndSend("login", null, map);

    }

    @Test
    public void receiveMsg() {
        Object o = rabbitTemplate.receiveAndConvert("q_app");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void createExchange() {
        //创建交换机
        amqpAdmin.declareExchange(new DirectExchange("amqp_exchange"));
        //创建队列
        amqpAdmin.declareQueue(new Queue("admin_queue"));
        //创建队列与交换机绑定规则
        //amqpAdmin.declareBinding(new Binding("admin"));

    }
}
