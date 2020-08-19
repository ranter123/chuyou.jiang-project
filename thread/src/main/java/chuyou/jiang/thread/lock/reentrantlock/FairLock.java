package chuyou.jiang.thread.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ranter
 * @Date: 2020/8/18 5:28 下午
 * @Description:
 */
public class FairLock {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i ++) {
            threads[i] = new Thread(new JobTask(printQueue));
        }
        for (int i = 0; i < 10; i ++) {
            threads[i].start();
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class JobTask implements Runnable {

        PrintQueue printQueue;

        public JobTask(PrintQueue printQueue) {
            this.printQueue = printQueue;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始打印了");
            printQueue.pringJob(new Object());
            System.out.println(Thread.currentThread().getName() + "打印完成了");
        }
    }

    static class PrintQueue {
        private Lock queuelock = new ReentrantLock(true);

        public void pringJob(Object document) {
            queuelock.lock();
            try {
                Long duration = (long) Math.random() * 100000;
                System.out.println(Thread.currentThread().getName() + "正在打印中，需要" + duration / 1000 + "秒");
            } finally {
                queuelock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queuelock.lock();
            try {
                Long duration = (long) Math.random() * 10000;
                System.out.println(Thread.currentThread().getName() + "正在打印中，需要" + duration / 1000 + "秒");
            } finally {
                queuelock.unlock();
            }
        }
    }
}
