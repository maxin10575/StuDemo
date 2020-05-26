package ConcurrencyTest.creat;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
* @Description:   使用Callable和FutureTask创建
* @Author: maxin
* @Date: 2020/3/10
* @Modified By:
* @Version: 1.0.0
*/
public class CreateThread3 {

    public static void main(String[] args) throws Exception {
        Callable<String> callable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask(callable);

        new Thread(futureTask).start();

        String result = futureTask.get();
        System.out.println("result = " + result);
    }

    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("calling");
            return "calling";
        }
    }
}
