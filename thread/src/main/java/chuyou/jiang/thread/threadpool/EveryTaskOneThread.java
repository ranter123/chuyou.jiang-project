package chuyou.jiang.thread.threadpool;

/**
 * @Author: ranter
 * @Date: 2020/8/16 9:50 下午
 * @Description:
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
