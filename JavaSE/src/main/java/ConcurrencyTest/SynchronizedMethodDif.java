package ConcurrencyTest;

/**
 * @program: studying
 * @description: synchronized:
 *               1. 方法使用 ACC_SYNCHRONIZED 标识
 *               2. 如果是static方法，锁是作用在类上，只有一个对象同时存在
 *               3. 如果是非static方法，锁作用在具体的类对象上，可以有多个对象同时存在
 *               4. 使用monitorenter和monitorexit指令，控制线程进出
 * @author: maxin
 * @create: 2020-03-06 16:51
 * @Modified By:
 * @Version: 1.0
 **/
public class SynchronizedMethodDif {

    /**
     * 有static
     * 锁是控制是在类级别的，静态方法一般是"类.方法"的调用方式
     */
    private static synchronized void soutStatic() {
        System.out.println(Thread.currentThread().getName() + " is sleeping ");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " : 1");
        System.out.println(Thread.currentThread().getName() + " finish sleeping ");
    }

    /**
     * 无static
     * 锁的控制是在对象级别的，对于同一个类的两个对象，它们可以同时执行，并不会相互影响
     */
    private synchronized void soutNonStatic() {
        System.out.println(Thread.currentThread().getName() + " is sleeping ");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " : 1");
        System.out.println(Thread.currentThread().getName() + " finish sleeping ");
    }


    public static void main(String[] args) throws Exception {
//        nonStaticTest();
        staticTest();
        System.in.read();
    }


    private static void nonStaticTest() {
        SynchronizedMethodDif dif1 = new SynchronizedMethodDif();
        SynchronizedMethodDif dif2 = new SynchronizedMethodDif();
        new Thread(() -> {
            dif1.soutNonStatic();
        }, "Thread-dif1").start();

        new Thread(() -> {
            dif2.soutNonStatic();
        }, "Thread-dif2").start();
    }


    private static void staticTest() {
        new Thread(() -> {
            soutStatic();
        }, "Thread-dif1").start();

        new Thread(() -> {
            soutStatic();
        }, "Thread-dif2").start();
    }
}
