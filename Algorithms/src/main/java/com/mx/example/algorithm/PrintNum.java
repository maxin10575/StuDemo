package com.mx.example.algorithm;

/**
 * TODO:
 *
 * @author mx
 * @version 1.0, 2019/2/25/15:00
 */
public class PrintNum
{
    private static int num=1;
    private static Object Lock = new Object();
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();
        Thread thread1 = new Thread(t1);
        //Thread thread2 = new Thread(t2);
        MyThread4 t4 = new MyThread4();
        MyThread3 t3 = new MyThread3(t4);
        Thread thread3 = new Thread(t3);
        Integer intr = new Integer("123");
//        thread1.start();
//        t2.start();
//        try {
//            thread1.join();
//            t2.join();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread3.start();

    }
    static class MyThread1 implements Runnable{
        @Override
        public void run() {
            synchronized (Lock){
                for(int i=0;i<15;i++){
                    System.out.println(Thread.currentThread().getName()+" "+num);
                    num++;
                    Lock.notify();
                    try {
                        Lock.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
    static class MyThread2 extends Thread{
        @Override
        public void run() {
            synchronized (Lock){
                for(int i=0;i<15;i++){
                    System.out.println(Thread.currentThread().getName()+" "+num);
                    num++;
                    Lock.notify();
                    try {
                        Lock.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

    }
    }
    static class MyThread3 extends Thread implements Runnable{
        private MyThread4 t4;
        private MyThread3(MyThread4 t){
            this.t4 = t;
        }
        public void run(){
            System.out.println("我是测试构造函数传Runnable实现类的");
        }

    }
    static class MyThread4 implements Runnable{

        @Override
        public void run() {
            System.out.println("我是测试Runnable接口的");
        }
    }
}
