package chuyou.jiang.thread.threadpool;

/**
 * @Author: ranter
 * @Date: 2020/8/16 9:52 下午
 * @Description:
 */
public class ForLoop {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            Thread thread = new Thread(new EveryTaskOneThread.Task());
            thread.start();
        }
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
