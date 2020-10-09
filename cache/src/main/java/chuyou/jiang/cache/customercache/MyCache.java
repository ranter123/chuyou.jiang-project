package chuyou.jiang.cache.customercache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ranter
 * @Date: 2020/8/24 8:41 下午
 * @Description:
 */
public class MyCache {

    private final HashMap<String, Integer> cache = new HashMap<>();

    public synchronized Integer computer(String userId) throws InterruptedException {
         Integer result = cache.get(userId);
         if (result == null) {
             result = doCompute(userId);
             cache.put(userId, result);
         }
         return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        System.out.println("开始计算了");
        Integer result = myCache.computer("13");
        System.out.println("第一次计算结果" + result);
        result = myCache.computer("13");
        System.out.println("第二次计算结果" + result);
    }
}
