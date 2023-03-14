package ConcurrencyTest.sqllock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: studying
 * @description:
 * 一、数据库的悲观锁乐观锁
 * SELECT ... LOCK IN SHARE MODE
 * 特性：
 * 共享锁，在事务内生效
 * 给符合条件的行添加共享锁，其他事务也可以继续给这些行添加共享锁，在锁释放前，其他事务无法对这些行进行 delete 和 update 操作。
 * 如果两个加了共享锁的事务同时更新一行，会发送 deadlock 死锁问题；
 * 某行已经有排它锁，就无法继续添加共享锁。
 * 不会阻塞正常读
 * 应用场景：
 *        适用于写两张存在关联关系的表数据，如 parent 和 child 表，写入child 表时需要确保 parentId 在 parent 表中已写入数据且不会被删除
 *
 *
 * SELECT ... FOR UPDATE
 * 排它锁，在事务内生效
 * 给符合条件的行添加的是排他锁，其他事务无法再加排它锁，在释放前，其他事务无法对这些行 delete 和 update 操作
 * 某行已经有共享锁，无法再添加排它锁
 * 第一个事务对某行加了排它锁，第二个事务继续加排它锁，第二个事务需要等待
 * 加锁有超时时间
 * 不会阻塞正常读
 *
 * 应用场景：
 *        并发更新会出现问题的场景，如金融账户转账，电商下单时的库存扣减，避免最终数字不准确
 *
 * 数据库乐观锁 CAS 思路
 * UPDATE set ... version = version + 1 where version = $version$
 * CAS 思路，使用 version 版本控制，保证同一时间只有一个事务可以更新成功
 * 根据影响的行数来判断是否更新成功，更新失败的继续重新获取version值更新，可以设置更大重试次数
 *
 * 乐观锁的使用场景：
 *
 * 电商下单的库存更新(商品的秒杀场景)：
 * 使用更新语句：update product_stock set number = number -1 where product_id = $productId$ and number -1 > 0
 * 判断更新影响的行数来成功还是失败
 *
 *
 *
 * 一、什么是 AQS？
 * AQS：AbstractQueuedSynchronizer
 * 提供一个框架来实现阻塞锁和相关的依赖于先进先出（FIFO）等待队列；
 * 各种同步组件的核心抽象实现类；
 * 多个请求时，管理等待队列，锁的占用和释放，中断、超时和通知等。
 *
 *
 * 二、AQS 扮演的作用
 *1. 可重入锁的公平非公平锁实现
 * 2. 可重入锁读写锁的公平非公平锁实现
 * 3. 信号量的公平非公平锁实现
 * 4. 线程池工作线程 Worker
 * 5. CountDownLatch 闭锁实现
 *
 * head
 * 等待队列的头结点，初始值为 null，延迟初始化；除了初始化，后续只能通过setHead()方法来设置；
 * 如果head节点存在，它的waitStatus不能为 CANCELLED；
 * tair
 * 等待队列的尾节点，初始值为null，延迟初始化；只能通过添加新节点的enq()方法来更新tair节点。
 * state
 * int 值
 * 0 表示AQS没有线程占用；
 * 1 表示有线程占用锁，后续线程需要排队等待获取锁
 * >1 表示已占用锁的线程重入了锁，state可表示重入锁的次数；
 *
 * exclusiveOwnerThread
 * 获得独占锁的线程
 *
 * waitStatus:
 * 0：初始状态
 * CANCELLED：节点是取消状态，等待超时或被中断
 * SIGNAL：后续节点处于等待状态，需要被唤醒
 * CONDITION：节点处于等待中
 * PROPAGATE：下一个需要被无条件传播
 *
 *
 * @author: maxin
 * @create: 2020-03-24 17:03
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                try {
                    reentrantLock.lock();
                    System.out.println(Thread.currentThread().getName() + " acquire lock...");
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                    System.out.println(Thread.currentThread().getName() + " release lock...");
                }

            };
            new Thread(runnable).start();
        }
    }
}
