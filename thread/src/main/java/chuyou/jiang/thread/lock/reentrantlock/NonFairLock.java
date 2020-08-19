package chuyou.jiang.thread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: ranter
 * @Date: 2020/8/18 6:59 下午
 * @Description:
 */
public class NonFairLock {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
}
