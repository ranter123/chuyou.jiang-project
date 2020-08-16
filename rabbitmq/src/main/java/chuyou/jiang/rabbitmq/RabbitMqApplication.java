package chuyou.jiang.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ranter
 * @Date: 2020/8/16 12:46 上午
 * @Description:
 */
@SpringBootApplication
@EnableRabbit
public class RabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }
}
