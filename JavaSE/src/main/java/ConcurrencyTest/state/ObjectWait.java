package ConcurrencyTest.state;

/**
* @Description:  Object类的方法，调用前必须拥有对象锁，例如在synchronized代码块内，
 *                 调用wait方法后，对象锁会释放，线程进入waiting等待状态
* @Author: maxin
* @Date: 2020/3/10
* @Modified By:
* @Version: 1.0.0
*/
public class ObjectWait {

    private static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        newThread("ThreadA").start();

        newThread("ThreadB").start();

        System.in.read();
    }

    private static Thread newThread(String threadName) {
        return new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    lock.notifyAll();
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait 开始");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " wait 结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, threadName);
    }
}
