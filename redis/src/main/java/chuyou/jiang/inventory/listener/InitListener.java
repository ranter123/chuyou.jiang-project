package chuyou.jiang.inventory.listener;

import chuyou.jiang.inventory.thread.RequestProcessorThreadPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author: ranter
 * @Date: 2020/10/7 3:06 下午
 * @Description: 系统初始化监听器
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始化线程池和工作队列
        RequestProcessorThreadPool.init();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
