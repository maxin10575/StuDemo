package ConcurrencyTest.threadpool;

import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/*** 
* @Description: 主线程组赛等待线程池执行结果，从共享变量中获取结果
 *              适用场景：需要程序提高执行速度，并且需要获取执行结果
 *                       需要有超时等待任务执行完毕，超时获取结果
* @Author: maxin
* @Date: 2020/3/23
* @Modified By:
* @Version: 1.0.0
*/
public class ThreadPoolCallableTest {


    @Test
    public void testGetTicketsInfo() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        int taskNum = 10;
        long mainStart = System.currentTimeMillis();
        List<Future<String>> futureList = new ArrayList<>();
        for (int taskIndex = 0; taskIndex < taskNum; taskIndex++) {
            Callable<String> callable =
                    () -> {
                        long start = System.currentTimeMillis();
                        long sleep = new Random().nextInt(5000);
                        Thread.sleep(sleep);
                        System.out.println(Thread.currentThread().getName() + " cost " + (System.currentTimeMillis() - start));
                        return "Airline-company-result-";
                    };
            Future<String> future = executorService.submit(callable);
            futureList.add(future);
        }

        for (Future<String> future : futureList) {
            try {
                String airlineResult = future.get(3, TimeUnit.SECONDS);
                System.out.println("Future result : " + airlineResult);
            } catch (Throwable th) {
                th.printStackTrace();
            }

        }
        System.err.println(Thread.currentThread().getName() + " cost " + (System.currentTimeMillis() - mainStart));

    }


    @Test
    public void testDoNotUseFuture() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Callable<String> callable =
                () -> {
                    long start = System.currentTimeMillis();
                    long sleep = new Random().nextInt(3000);
                    Thread.sleep(sleep);
                    System.out.println(Thread.currentThread().getName() + " cost " + (System.currentTimeMillis() - start));
                    return "Airline-company-result-1";
                };

        Future<String> future = executorService.submit(callable);

        String result = future.get();
        System.out.println(result);

    }
}
