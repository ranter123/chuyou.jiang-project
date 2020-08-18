package chuyou.jiang.thread.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ranter
 * @Date: 2020/8/18 10:55 上午
 * @Description:
 */
public class MustUnlock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
