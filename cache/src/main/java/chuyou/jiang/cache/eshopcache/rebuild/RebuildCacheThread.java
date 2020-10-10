package chuyou.jiang.cache.eshopcache.rebuild;

import chuyou.jiang.cache.eshopcache.model.ProductInfo;
import chuyou.jiang.cache.eshopcache.queue.RebuildCacheQueue;
import chuyou.jiang.cache.eshopcache.zookeeper.ZooKeeperSession;

/**
 * @Author: ranter
 * @Date: 2020/10/9 4:17 下午
 * @Description: 缓存 重建
 */
public class RebuildCacheThread implements Runnable {

    @Override
    public void run() {
        RebuildCacheQueue rebuildCacheQueue = RebuildCacheQueue.getInstance();
        ZooKeeperSession zooKeeperSession = ZooKeeperSession.getInstance();

        while (true) {
            //拿出缓存
            ProductInfo productInfo = rebuildCacheQueue.takeProductInfo();
            zooKeeperSession.acquireDistrubuteLock(productInfo.getId());
            //写入redis缓存。


            //释放分布式锁
            zooKeeperSession.releaseDistributeLock(productInfo.getId());
        }

    }
}
