package chuyou.jiang.thread.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ranter
 * @Date: 2020/8/18 11:52 上午
 * @Description: 中断锁
 */
public class LockInterruptibly implements  Runnable{

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly t1 = new LockInterruptibly();
        LockInterruptibly t2 = new LockInterruptibly();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        thread2.interrupt();

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "拿到了锁");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "等锁期间被中断");
            e.printStackTrace();
        }
    }
}
