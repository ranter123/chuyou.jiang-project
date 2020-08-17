package chuyou.jiang.thread.threadpool;

import java.text.SimpleDateFormat;

/**
 * @Author: ranter
 * @Date: 2020/8/17 6:46 下午
 * @Description:
 */
public class ThreadSafeDateFormetter {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };


}
