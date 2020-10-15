package chuyou.jiang.hystrix.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ranter
 * @Date: 2020/10/11 10:20 上午
 * @Description:
 */
public class LocalCache {

    public static Map<Long, String> cityMap = new HashMap<>();

    static {
        cityMap.put(1L, "北京");
    }
}
