package com.mx.example.highConcurrency;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class HighConcurrency {
    private static int start = 1;
    private static Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        //定义两个线程，保存在threads
        List<Thread> threads = IntStream.range(1, 3).mapToObj(HighConcurrency::create).collect(toList());
        //启动两个线程
        threads.forEach(Thread::start);
        //执行两个线程的join方法
        for (Thread thread : threads) {
            thread.join();
        }
        //main线程循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
        }
        System.out.println(start);
    }

    //构造一个简单的线程，每个线程只是简单的循环输出
    private static Thread create(int seq) {
        return new Thread(() ->
        {
            for (int i = 0; i < 15; i++) {
                synchronized (LOCK) {
                    System.out.print(Thread.currentThread().getName() + "#" + start++ + "\n");
                    LOCK.notifyAll();
                    //shortSleep();
                    try {
                        LOCK.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
