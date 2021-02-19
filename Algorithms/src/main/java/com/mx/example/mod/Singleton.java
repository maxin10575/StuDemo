package com.mx.example.mod;

/**
 * TODO:单例懒汉模式
 *
 * @author dangzw
 * @version 1.0, 2019/1/15/15:31
 */
public class Singleton {
    private Singleton() {
    }

    private static Singleton singleton;

    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public void printHello() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        singleton.getInstance().printHello();
    }
}

//上述单例模式在单线程环境下不存在问题，但是处于并发场景下就需要考虑线程安全了，可以采用“双检索”来实现
//采用volatile修饰能够提供可见性，以及保证getInstance返回的是初始化完全的对象
//在同步之前进行null，以尽量避免进入昂贵的同步块
//直接在class级别进行同步，保证线程安全的类方法调用
class Singleton2 {
    private static volatile Singleton2 singleton2 = null;

    private Singleton2() {

    }

    public static Singleton2 getSingleton2() {
        if (singleton2 == null) {
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }

}
