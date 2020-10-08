package chuyou.jiang.inventory.thread;

import chuyou.jiang.inventory.request.ProductInventoryCacheRefreshRequest;
import chuyou.jiang.inventory.request.ProductInventoryDBUpdateRequest;
import chuyou.jiang.inventory.request.Request;
import chuyou.jiang.inventory.request.RequestQueue;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @Author: ranter
 * @Date: 2020/10/7 3:28 下午
 * @Description: 执行请求的工作进程
 */
public class WorkThread implements Callable<Boolean> {

    /**
     * 自己监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;

    public WorkThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while (true) {
                Request request = queue.take();
                System.out.println("=============日志=============: 工作线程处理请求，商品ID=" + request.getProductId());
                Boolean forceRefreash = request.isForceRefresh();
                //读请求去重
                RequestQueue requestQueue = RequestQueue.getInstance();
                Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();

                if (request instanceof ProductInventoryDBUpdateRequest) {
                    //如果是更新数据库请求，那么将productId 对应的标识位设置为true
                    flagMap.put(request.getProductId(), true);
                } else if (request instanceof ProductInventoryCacheRefreshRequest) {
                    //如果是缓存刷新的请求，那么判断标识不为空，而且是true. 那么就说明之前有一个商品数据的更新请求
                    Boolean flag = flagMap.get(request.getProductId());
                    if (flag != null && flag) {
                        flagMap.put(request.getProductId(), false);
                    }

                    //去除空请求
                    if (flag == null) {
                        flagMap.put(request.getProductId(), false);
                    }

                    //如果是缓存请求，而且发现标识不为空，但是标识是false
                    //如果说明已经有一个数据库更新请求 + 一个读请求
                    if (flag != null && !flag && !forceRefreash) {
                        // 对于这种请求，直接过滤掉，不用放到后面队列中去了。
                        continue;
                    }

                }
                //执行这个request操作
                request.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
