package chuyou.jiang.thread.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: ranter
 * @Date: 2020/8/19 9:40 上午
 * @Description: 自璇锁
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {

        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }
}
