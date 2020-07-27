package ConcurrencyTest;

/**
 * @program: studying
 * @description: 线程死锁举例
 * @author: maxin
 * @create: 2020-03-06 11:07
 * @Modified By:
 * @Version: 1.0
 *
 * 死锁产生的四个必要条件：
     * 1、互斥使用，即当资源被一个线程使用(占有)时，别的线程不能使用
     * 2、不可抢占，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放。
     * 3、请求和保持，即当资源请求者在请求其他的资源的同时保持对原有资源的占有。
     * 4、循环等待，即存在一个等待队列：P1占有P2的资源，P2占有P3的资源，P3占有P1的资源。这样就形成了一个等待环路。
 * 处理死锁
 *     1.预防死锁
 * 　　   通过设置某些限制条件，去破坏产生死锁的四个必要条件中的一个或几个。此方法较易实现，已被广泛使用，但会降低系统资源利用率。
 * 　　2.避免死锁
 * 　　   在资源的动态分配过程中，使用某种方法去防止系统进入不安全状态。
 * 　　3.死锁检测并解除
 * 　　   通过系统所设置的检测机构，可以及时发现与死锁有关的进程和资源，然后将进程从死锁状态中解脱出来，常用的措施是撤销或挂起一些进程(剥夺)，
 *        以便回收某些资源，再将这些资源分配给已处于阻塞状态的进程，使之变为就绪态，使之继续运行，此方法实施难度比较大。
 * 定位死锁问题
 *          ·jps：例举正在运行的饿虚拟机进程并显示虚拟机的主类以及这些进程的唯一ID(PID)
 *         ·jstack：用于JVM当前时刻的线程快照，得到JVM当前每一条线程正在执行的堆栈信息，定位线程长时间卡顿问题。
 *
 *
 * 避免和处理死锁问题：
 *  1.不使用锁，不使用两把及以上的锁
 *  2.必须使用2把以上锁时候，确保在整个应用程序中获取锁的顺序是一致的
 *  3.尝试获取具有超时释放的锁，例如Lock中的tryLock来获取锁
 *  4.当发生了java-level的锁时，重启程序来干掉进程/线程
 *
 **/


/**
   volatile
      1. 用来保证多线程间对变量的内存可见性，将最新变量值及时通知给其他线程
      2. 禁止volatile前后的程序指令进行重排序
      3. 不保证线程安全，不可用于数字的线程安全递增
   使用场景
        1. 修饰状态变量，保证各线程可以看到最新的内存值
        · 单实例对象构造，避免多线程情况下由于内存不可见而重复多次构造对象
   synchronized与volatile区别
        · synchronized是用于同步锁控制，具有原子性，控制同一时间只有一个线程执行一个方法或代码块
        · volatile只保证线程间的内存可见性，不具备锁的特性，无法保证修饰对象的原子性*/

public class DeadLockSample {
// console:
//    ThreadA holding lockFirst
//    ThreadA waiting lockSecond
//    ThreadB holding lockFirst
//    ThreadB waiting lockSecond

    private final static Object lock1 = new Object();
    private final static String lock2 = new String();

    public static void main(String[] args) throws Exception {
        newThread("ThreadA", lock1, lock2).start();
        newThread("ThreadB", lock2, lock1).start();
        System.in.read();
    }

    private static Thread newThread(String threadName, Object lockFirst, Object lockSecond) {
        return new Thread(() -> {

            synchronized (lockFirst) {
                System.out.println(Thread.currentThread().getName() + " holding lockFirst");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting lockSecond");

                synchronized (lockSecond) {
                    System.out.println(Thread.currentThread().getName() + " holding lockSecond");
                }
            }
        }, threadName);
    }
}
