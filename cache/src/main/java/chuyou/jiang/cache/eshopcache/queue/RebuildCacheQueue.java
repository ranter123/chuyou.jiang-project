package chuyou.jiang.cache.eshopcache.queue;

import chuyou.jiang.cache.eshopcache.model.ProductInfo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: ranter
 * @Date: 2020/10/9 4:10 下午
 * @Description:
 */
public class RebuildCacheQueue {

    private ArrayBlockingQueue<ProductInfo> queue = new ArrayBlockingQueue<>(1000);

    /**
     * 把商品添加到队列中去
     * @param productInfo
     */
    public void putProductInfo(ProductInfo productInfo) {
        try {
            queue.put(productInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从队列中取出商品
     * @return
     */
    public ProductInfo takeProductInfo() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class Singleton {
        private static RebuildCacheQueue instance;

        static {
            instance = new RebuildCacheQueue();
        }

        private static RebuildCacheQueue getInstance() {
            return instance;
        }
    }

    public static RebuildCacheQueue getInstance() {
        return Singleton.getInstance();
    }

    public static void init() {
        getInstance();
    }
}
