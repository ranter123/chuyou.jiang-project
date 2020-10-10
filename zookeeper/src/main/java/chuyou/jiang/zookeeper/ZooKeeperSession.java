package chuyou.jiang.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: ranter
 * @Date: 2020/10/9 2:46 下午
 * @Description:
 */
public class ZooKeeperSession {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    public ZooKeeperSession() {
        // 去连接zookeeper server 创建会话的时候是异步执行的。
        // 所以要创建一个监听器，说告诉我们什么时候真正完成
        try {
            this.zooKeeper = new ZooKeeper(
                    "10.211.55.5:2181,10.211.55.6:2181,10.211.55.7:2181",
                    50000,
                    new ZooKeeperWatcher()
              );

            try {
                connectedSemaphore.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ZooKeeper session established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ZooKeeperWatcher implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("Receive watched event: " + watchedEvent);
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                connectedSemaphore.countDown();
            }
        }
    }

    /**
     * 创建一个分布式锁
     * @param productId
     */
    public void acquireDistrubuteLock(Long productId) {
        String path = "/product-lock-" + productId;

        try {
            zooKeeper.create(path, "".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("success to acquire lock for product[id=" + productId + "]");
        } catch (Exception e) {
            //如果那个商品对应的锁的node, 已经存在了，就是已经被别人加锁了。那么这里会报错
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(20);
                    zooKeeper.create(path, "".getBytes(),
                            ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    count ++;
                    continue;
                }
                System.out.println("success to acquire lock for product[id=" + productId + "] after " + count + "times try....");
                break;
            }
        }

    }

    public  void releaseDistributeLock(Long poroductId) {
        String path = "/product-lock-" + poroductId;
        try {
            zooKeeper.delete(path, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class Singleton {
        private static ZooKeeperSession instance;

        static {
            instance = new ZooKeeperSession();
        }

        public static ZooKeeperSession getInstance() {
            return instance;
        }
    }

    /**
     * 获取单例
     * @return
     */
    public static ZooKeeperSession getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化单例
     */
    public static void init() {
        getInstance();
    }
}
