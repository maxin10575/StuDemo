package ConcurrencyTest.semaphoretest;

//import Concurrency.annoations.NotThreadSafe;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static ConcurrencyTest.semaphoretest.SemaphoreTest.clientTotal;

/**
 * @Author maxin
 * @Date 2019-10-24 18:26
 * @ClassName ConcurrencyTest
 * @Description 并发测试  Semaphore 是 synchronized 的加强版，作用是控制线程的并发数量。
 * @Version 1.0
 **/

//@NotThreadSafe
public class SemaphoreTest {

    //请求总数
    public static int clientTotal = 10;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码
        final Semaphore semaphore = new Semaphore(3);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        /**
         * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许指定个数的线程进入，
         * 因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
         * */

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ":doSomething start-" + sf.format(new Date()));
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ":doSomething end-" + sf.format(new Date()));
                    semaphore.release();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
            count++;
        }
        System.out.println("count ======"+count);
        countDownLatch.await();
        System.out.println("线程执行完毕，开始执行主线程");
        executorService.shutdown();

    }
}