package chuyou.jiang.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ranter
 * @Date: 2020/8/17 10:47 上午
 * @Description:
 */
public class ScheduleThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(new FixedThreadPool.Task(), 5, TimeUnit.SECONDS);
    }
}
