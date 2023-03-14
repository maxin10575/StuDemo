package ConcurrencyTest.creat;

import java.util.concurrent.*;

/**
* @Description: 将Runnable或Callable放到线程池ExecutorService中执行
 *    · 实现Callable/Runnable接口，重写call/run方法
 *    · 构建ExecutorService线程池对象，调用线程池execute或submit方法执行线程
 *    · 对于submit方式提交，使用Future来获取线程执行结果
* @Author: maxin
* @Date: 2020/3/12
* @Modified By:
* @Version: 1.0.0
 */

public class CreateThread4 {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //1 execute Runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread running");
            }
        };
        executorService.execute(runnable);
        //2 submit Runnable
        Future runnableFuture = executorService.submit(runnable);
        Object futureObject = runnableFuture.get();
        System.out.println("futureObject = " + futureObject);

        //3 submit callable
        Callable callable = new MyCallable();
        Future<String> future = executorService.submit(callable);
        String result = future.get();
        System.out.println("future result : " + result);
    }

    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("calling");
            return "calling";
        }
    }
}
