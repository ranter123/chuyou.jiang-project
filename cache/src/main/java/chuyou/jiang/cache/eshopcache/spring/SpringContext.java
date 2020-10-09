package chuyou.jiang.cache.eshopcache.spring;

import org.springframework.context.ApplicationContext;

/**
 * @Author: ranter
 * @Date: 2020/10/8 4:36 下午
 * @Description: spring 上下文
 */
public class SpringContext {

    private static ApplicationContext applicationContext;


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.applicationContext = applicationContext;
    }
}
