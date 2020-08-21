package chuyou.jiang.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author: ranter
 * @Date: 2020/8/19 12:48 下午
 * @Description: 变量升级成原子类
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    static Candidate tom;
    static Candidate petter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 100; i ++) {
            petter.score ++;
            scoreUpdater.getAndIncrement(tom);
        }
    }

    public static class Candidate {
        volatile int score;
    }
}
