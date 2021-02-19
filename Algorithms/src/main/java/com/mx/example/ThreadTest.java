package com.mx.example;

/**
 * TODO:
 *
 * @author dangzw
 * @version 1.0, 2019/2/26/14:31
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread.currentThread().setName("Thread main");
        MyThread thread = new MyThread();
        thread.start();

    }
    static class MyThread extends Thread{
        private MyThread(){
            this.setName("Thread t");
            this.printThreadName();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            super.run();
        }

        private void printThreadName() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
