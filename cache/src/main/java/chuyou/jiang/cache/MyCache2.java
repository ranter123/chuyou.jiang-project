package chuyou.jiang.cache;

import chuyou.jiang.cache.computeable.Computable;
import chuyou.jiang.cache.computeable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Author: ranter
 * @Date: 2020/8/24 9:10 下午
 * @Description: futrue 避免重复计算
 */
public class MyCache2<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public MyCache2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
//        System.out.println("进入缓存机制");
//        V result = cache.get(arg);
//        if (result == null) {
//            result = c.compute(arg);
//            cache.put(arg, result);
//        }
//        return result;
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> callable = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(callable);
            f = ft;
            cache.put(arg, ft);
            ft.run();
            System.out.println("从FutrueTask调用了计算函数");
        }
        return f.get();
    }

    public static void main(String[] args) throws Exception {
        MyCache2<String, Integer> expensiveComputer = new MyCache2<String, Integer>(new ExpensiveFunction());
        Integer result = expensiveComputer.compute("666");
        System.out.println("第一次计算结果" + result);
        result = expensiveComputer.compute("666");
        System.out.println("第二次计算结果" + result);
    }
}
