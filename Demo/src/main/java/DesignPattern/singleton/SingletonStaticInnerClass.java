package DesignPattern.singleton;

/**
 * @Author maxin
 * @Date 2019-11-19 11:16
 * @ClassName SingletonStaticInnerClass
 * @Description 静态内部类：
 *              1、这种方式使用了类状态的机制来保证初始化实例时只有一个线程
 *              2.静态内部类方式在Sngleton类被装载时并不会立即实例化，而是在需要实例化时，
 *                调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化
 *              3.类的静态属性只会在第一次加载类的时候初始化，所以这里JVM帮助我们保证了线程安全性。
 *               在类进行初始化时，别的线程是无法进入的
 *             4.避免了线程不安全，利用静态内部类特点实现延迟加载，效率高
 *             5.推荐使用
 * @Version 1.0
 **/
public class SingletonStaticInnerClass {
}

class SingletonStat {
    //构造器私有化
    private SingletonStat(){}

    //写一个静态内部类，该类中有一个静态属性SingletonStat
    private static class SingletonStatInstance {
        private static final SingletonStat  INSTANCE = new SingletonStat();
    }
    //提供一个静态的公有方法，直接返回SingletonStatInstance.INSTANCE
    public static SingletonStat getInstance() {
        return SingletonStatInstance.INSTANCE;
    }
}