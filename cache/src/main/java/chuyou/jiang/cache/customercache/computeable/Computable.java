package chuyou.jiang.cache.customercache.computeable;

/**
 * @Author: ranter
 * @Date: 2020/8/24 9:00 下午
 * @Description: 计算函数computer, 用来代表耗时计算，每个计算器都要实现这个接口，这样就可以无侵入实现缓存功能
 */
public interface Computable <A, V>{

    V compute(A arg) throws Exception;
}