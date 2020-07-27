package ConcurrencyTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 1.synchronized是java语言的关键字，由虚拟机字节码指令实现
 *                  ReentrantLock是java sdk提供的API级别锁实现
 *               2.synchronized可以在方法级别锁，ReentrantLock则不行
 *               3.RenntrantLock可以通过方法tryLock等待指定时间的锁，超时返回，synchronized则不行
 *               4.RenntrantLock提供了公平锁和非公平锁实现，synchronized只有非公平锁
 *
 *          ReentrantLock：
 *               ReentrantLock 是一个可重入的互斥（/独占）锁，又称为“独占锁”。
 *               ReentrantLock通过自定义队列同步器（AQS-AbstractQueuedSychronized，是实现锁的关键）来实现锁的获取与释放。
 *
 *               可重入锁了：就是一个线程在获取了锁之后，再次去获取了同一个锁，这时候仅仅是把状态值进行累加。如果线程A释放了一次锁，
 *               仅仅是把状态值减了，只有线程A把此锁全部释放了，状态值减到0了，其他线程才有机会获取锁。当A把锁完全释放后，state恢复为0，
 *               然后会通知队列唤醒B线程节点，使B可以再次竞争锁。当然，如果B线程后面还有C线程，C线程继续休眠，除非B执行完了，通知了C线程。
 *               注意，当一个线程节点被唤醒然后取得了锁，对应节点会从队列中删除
 *
 *               “独占”，就是在同一时刻只能有一个线程获取到锁，而其它获取锁的线程只能处于同步队列中等待，只有获取锁的线程释放了锁，后继的线程才能够获取锁。
 *               “可重入”，就是支持重进入的锁，它表示该锁能够支持一个线程对资源的重复加锁。
 *
 *
 * synchronized与ReentrantLock
 * 相同点：
 * 都用于资源加多，控制代码同一时间只有单线程在执行
 * 当一个线程获取了锁，其他线程均等待
 * 均是可重入锁
 * 不同点：
 * synchronized是Java语言关键字，由虚拟机字节码指令实现
 * ReentrantLock是Java jdk提供的api级别锁实现
 * synchronized可在方法级别，但ReentrantLock不行
 * ReentrantLock可通过tryLock等待指定时间的锁，超时返回，synchronized不行
 * synchronized只有非公平锁，ReentrantLock有公平锁和非公平锁
 *
 *
 * @Author: maxin
 * @Date: 2020/3/9
 * @Modified By:
 * @Version: 1.0.0
 */
public class SynchronizedReentrant {
    private static Object LOCK = new Object();

    public static void main(String[] args) {
        reentrant();
    }


    private static void reentrant() {
        synchronized (LOCK) {
            System.out.println("hold Lock");
            synchronized (LOCK) {
                System.out.println("hold Lock again");
            }
        }
    }


    private static void useReentantLock() {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println("hold Lock");
        } finally {
            lock.unlock();
        }
    }
}
