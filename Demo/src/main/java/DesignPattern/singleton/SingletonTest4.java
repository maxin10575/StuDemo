package DesignPattern.singleton;

/**
 * @Author maxin
 * @Date 2019-11-19 10:04
 * @ClassName SingletonTest4
 * @Description
 *        懒汉式（线程安全，同步方法）
 *          1.解决了线程不安全问题
 *          2.效率太低了，每个线程想获得类的实例的时候，执行getInstance方法都要进行
 *            同步，而其实这个方法只执行一次实例化代码就够了，后面的想要获得该类实例
 *            直接return就行了。方法进行同步效率太低
 *         结论：实际开发中，不推荐使用
 * @Version 1.0
 **/
public class SingletonTest4 {
}
class Singleton4 {
    private static Singleton4 singleton4;
    private Singleton4(){}
    //加入同步模块，解决线程不安全问题
    public  static synchronized Singleton4 getInstance() {
        if(singleton4 == null){
            singleton4 = new Singleton4();
        }
        return singleton4;
    }
}

/*
* 同步代码块，线程不安全 根本达不到线程安全。完全不行
 */
class Singleton6 {
    private static Singleton6 singleton6;
    private Singleton6(){}
    public  static  Singleton6 getInstance() {
        if(singleton6 == null){
            synchronized (Singleton6.class) {
                singleton6 = new Singleton6();
            }
        }
        return singleton6;
    }
}