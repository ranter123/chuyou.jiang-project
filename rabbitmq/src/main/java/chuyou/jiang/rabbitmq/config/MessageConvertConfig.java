package chuyou.jiang.rabbitmq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ranter
 * @Date: 2020/8/16 3:15 下午
 * @Description:
 */
@Configuration
public class MessageConvertConfig {

    @Bean
    MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }
}
