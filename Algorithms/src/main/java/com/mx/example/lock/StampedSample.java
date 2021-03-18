package com.mx.example.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * TODO:spin Lock 自旋锁实现
 *
 * @author mx
 * @version 1.0, 2019/1/21/11:31
 */
public class StampedSample {
    /**
     * JDK8以后开始有了新的锁机制，StampedLock 在提供类似读写锁的同时，还支持优化读模式
     * 优化读基于假设，大多数情况下读操作并不会和写操作冲突，其逻辑是先试着修改，然后通过validate方法
     * 确认时候进入了写模式，如果没有进入，就成功避免了开销，如果进入则尝试获取读锁
     * 自旋锁：竞争锁失败的线程，并不会真实的在操作系统层面挂起等待，而是JVM会让线程做几个空循环（基于预测
     * 在不久的将来就能获得锁），经过若干次循环，如果获得锁进入临界区，如果不能获得锁，才真正挂起。
     * 适用场景：自旋可以减少线程的阻塞，对于锁竞争不激烈，且占用锁时间短的代码块来说有较大的性能提升。因为
     * 自旋消耗会小于线程阻塞挂起操作的消耗。
     * 在单核CPU自旋锁是无用的，因为基于CAS的轮询会占用CPU，导致无法做线程切换。
     */
    private final StampedLock sl = new StampedLock();

    public void mutate() {
        long stamp = sl.writeLock();
        try {
            write();
        } finally {
            sl.unlock(stamp);
        }
    }

    public void write() {

    }

    public Data access() {
        long stamp = sl.tryOptimisticRead();
        Data data = read();
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                data = read();
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return data;
    }

    class Data {

    }

    public Data read() {
        return null;
    }

}
