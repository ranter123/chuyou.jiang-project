package chuyou.jiang.thread.lock.readwirtelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: ranter
 * @Date: 2020/8/18 7:28 下午
 * @Description: 演示非公平和公平的ReentrantReadWriteLock
 */
public class NonfairBargeDemo {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "已经获取到了读锁");
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }

    }

    private static void write() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "已经获取到了写锁");
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了写锁");
            writeLock.unlock();
        }

    }

    public static void main(String[] args) {
        new Thread(() -> write(), "thread1").start();
        new Thread(() -> read(), "thread2").start();
        new Thread(() -> read(), "thread3").start();
        new Thread(() -> write(), "thread4").start();
        new Thread(() -> read(), "thread5").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread[] threads = new Thread[1000];
                for (int i = 0; i < 1000; i ++) {
                    threads[i] = new Thread(() -> read(), "子线程创建的thread" + i);
                }
                for (int i = 0; i < 1000; i ++) {
                    threads[i].start();
                }
            }
        }).start();
    }
}
