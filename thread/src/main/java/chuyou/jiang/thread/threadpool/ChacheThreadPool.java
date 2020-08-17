package chuyou.jiang.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: ranter
 * @Date: 2020/8/17 10:45 上午
 * @Description:
 */
public class ChacheThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i ++) {
            executorService.execute(new FixedThreadPool.Task());
        }
    }
}
