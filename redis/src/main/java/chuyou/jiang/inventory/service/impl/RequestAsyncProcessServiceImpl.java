package chuyou.jiang.inventory.service.impl;

import chuyou.jiang.inventory.request.Request;
import chuyou.jiang.inventory.request.RequestQueue;
import chuyou.jiang.inventory.service.RequestAsyncProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: ranter
 * @Date: 2020/10/7 5:18 下午
 * @Description:
 */
@Service("requestAsyncProcessService")
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

    @Override
    public void process(Request request) throws InterruptedException {

        //做请求的路由，根据每个请求的商品ID，路由到对应的队列中去。
        ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
        queue.put(request);
    }

    private ArrayBlockingQueue getRoutingQueue(Integer productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();
        // 先获取productId的hash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        //对 hash值取模，将hash值路由到对应的内存队列中
        int index = (requestQueue.queueSize() - 1) & hash;

        System.out.println("===============日志===============：路由内存队列，商品ID=" + productId + ", 队列索引：" + index);
        return requestQueue.getQueue(index);
    }
}
