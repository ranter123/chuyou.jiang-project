package chuyou.jiang.thread.utils;

import java.util.concurrent.locks.ReentrantReadWriteLock;


public class DateUtil {

    private static DateUtil instance = new DateUtil();

    public ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) throws InterruptedException{
        final DateUtil dateUtil = DateUtil.instance;
        try{
            new Thread(()->{
                try{
                    System.out.println("----线程1获取写锁----");
                    dateUtil.writeLock.lock();
                    Thread.sleep(5000);
                }catch (Exception error) {
                    error.printStackTrace();
                }finally {
                    dateUtil.writeLock.unlock();
                    System.out.println("----线程1释放写锁----");
                }
            }).start();

            new Thread(()->{
                try{
                    System.out.println("----线程2获取写锁----");
                    dateUtil.writeLock.lock();
                }catch (Exception error) {
                    error.printStackTrace();
                }finally {
                    dateUtil.writeLock.unlock();
                    System.out.println("----线程2释放写锁----");
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
