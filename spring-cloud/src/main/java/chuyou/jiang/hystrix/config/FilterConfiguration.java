package chuyou.jiang.hystrix.config;

import chuyou.jiang.hystrix.filter.HystrixRequestContextFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: ranter
 * @Date: 2020/10/12 10:15 上午
 * @Description:
 */
@Configuration
public class FilterConfiguration {

    @Bean
    FilterRegistrationBean filterRegistrationBean () {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HystrixRequestContextFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
