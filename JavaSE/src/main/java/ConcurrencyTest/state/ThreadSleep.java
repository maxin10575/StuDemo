package ConcurrencyTest.state;

/**
* @Description: 线程休眠，主动让出当前CPU时间，在指定时间过后，CPU会继续执行该线程
 *                  sleep方法不会释放当前所持有的锁。
* @Author: maxin
* @Date: 2020/3/10
* @Modified By:
* @Version: 1.0.0
*/
public class ThreadSleep {

    public static Object object = new Object();

    public static void main(String[] args) throws Exception {

        newThread("ThreadA").start();

        newThread("ThreadB").start();

        System.in.read();
    }

    private static Thread newThread(String threadName) {
        return new Thread(() -> {
            synchronized (object) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    if (i == 10) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " start to sleep");
                            Thread.sleep(1000L);
                            System.out.println(Thread.currentThread().getName() + " finished sleeping");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, threadName);
    }
}
