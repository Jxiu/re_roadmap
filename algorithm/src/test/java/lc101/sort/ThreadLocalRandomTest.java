package lc101.sort;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {
    /**
     * 在静态变量中共享ThreadLocalRandom会导致多个线程有相同的输出
     */
    static ThreadLocalRandom random = ThreadLocalRandom.current();

    @Test
    public void multiThreadTest(){
        int threadCount = 4;
        CyclicBarrier barrier = new CyclicBarrier(threadCount);
        Runnable runnable = ()->{
            System.out.println(Thread.currentThread().getName() + " random number:" + random.nextInt());
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        ExecutorService executor  = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount * 2; i++) {
            executor.execute(runnable);
        }

        executor.shutdown();
    }

}
