package chuyou.jiang.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: ranter
 * @Date: 2020/8/17 11:07 上午
 * @Description:
 */
public class ShutDownThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i ++) {
            executorService.execute(new ShutDownTask());
        }
        executorService.shutdown();
//        executorService.execute(new ShutDownTask());
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
    }

    static class ShutDownTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
