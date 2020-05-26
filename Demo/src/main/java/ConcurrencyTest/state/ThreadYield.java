package ConcurrencyTest.state;

import org.junit.Test;
/**
* @Description: yield : 当前线程就会退出CPU时间片，让其他线程或当前线程使用
 *                  CPU时间片执行
* @Author: maxin
* @Date: 2020/3/10
* @Modified By:
* @Version: 1.0.0
*/
public class ThreadYield {

    public static void main(String[] args) throws Exception {
        newThread("ThreadA").start();

        newThread("ThreadB").start();

        System.in.read();
    }


    private static Thread newThread(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                if (i == 10) {
                    Thread.yield();
                }
            }
        }, threadName);
    }
}
