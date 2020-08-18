package chuyou.jiang.thread.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ranter
 * @Date: 2020/8/18 10:59 上午
 * @Description: 用tryLock 避免死锁
 */
public class TryLockDeadLock implements Runnable{

    int flag = 1;

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadLock tr1 = new TryLockDeadLock();
        TryLockDeadLock tr2 = new TryLockDeadLock();
        tr1.flag = 1;
        tr2.flag = 0;
        new Thread(tr1).start();
        new Thread(tr2).start();
    }

    @Override
    public void run() {
        for (;;) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("线程1获取到了锁1");
                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println("线程1 获取到了锁2");
                                    break;
                                } finally {
                                    lock2.unlock();
                                }
                            } else {
                                System.out.println("线程1 获取锁2 失败");
                            }
                        } finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println("线程1 获取锁2 失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }  else {
                try {
                    if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("线程2获取到了锁2");
                            if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println("线程2 获取到了锁1");
                                    break;
                                } finally {
                                    lock1.unlock();
                                }
                            } else {
                                System.out.println("线程2 获取锁1 失败");
                            }
                        } finally {
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println("线程2 获取锁2 失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
