package ConcurrencyTest.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @Description:
* @Author: maxin
* @Date: 2020/3/23
* @Modified By:
* @Version: 1.0.0
*/
public class ThreadPoolCreateTest {


    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " running");
        });
    }
}
