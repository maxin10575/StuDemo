package ConcurrencyTest.state;

/**
 * @Description: 等待该线程死亡/终止，当前线程会等待调用该方法的线程执行完毕后
 * 才能继续执行。
 *
 * void join（）
 *  *该方法允许一个线程调用另一个线程的join方法，使
 *  *得调用方法的线程进入阻塞状态，直到join方法所属对象
 *  *结束后才解除阻塞继续执行。
 *  *例如A线程调用了B线程的join方法
 *  *那么A线程进入阻塞状态，直到B线程结束，A才会解除
 *  *阻塞继续运行
 *
 * @Author: maxin
 * @Date: 2020/3/10
 * @Modified By:
 * @Version: 1.0.0
 */
public class ThreadJoin {


    public static void main(String[] args) throws InterruptedException {
        Thread threadA = newThread("ThreadA");

        Thread threadB = newThread("ThreadB");

        threadA.start();
        threadB.start();

   /*     threadA.join();
        System.out.println("等待ThreadA执行完毕/终止");*/

        threadB.join();
        System.out.println("等待ThreadB执行完毕/终止");

        System.out.println("主线程继续执行......");


    }

    private static Thread newThread(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                if (i == 2) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, threadName);
    }
}
