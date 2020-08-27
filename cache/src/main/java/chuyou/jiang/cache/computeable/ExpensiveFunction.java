package chuyou.jiang.cache.computeable;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ranter
 * @Date: 2020/8/24 9:06 下午
 * @Description:
 */
public class ExpensiveFunction implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        return Integer.valueOf(arg);
    }
}
