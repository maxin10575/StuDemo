package com.mx.example.highConcurrency;

public class TestThread implements Runnable{
    @Override
    public void run() {
        System.out.println("测试线程创建实现Runnalbe接口");
    }
}
