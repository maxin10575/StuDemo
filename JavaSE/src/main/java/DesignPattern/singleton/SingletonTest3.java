package DesignPattern.singleton;

import com.sun.jmx.snmp.SnmpInt;

/**
 * @Author maxin
 * @Date 2019-11-18 17:57
 * @ClassName SingletonTest3
 * @Description
 *       懒汉式（线程不安全）：
 *         起到了Lazy Loading效果，但只能在单线程下使用
 *         如果在多线程下，一个线程进入了if(singleton ==null)判断语句块，还未来得及
 *         往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程
 *         环境不可使用这种方式
 *         结论：实际开发中，不要使用这种方式
 * @Version 1.0
 **/
public class SingletonTest3 {
    public static void main(String[] args) {
        System.out.println("懒汉式，线程不安全");
        Singleton3 a1 = Singleton3.getInstance();
        Singleton3 a2 = Singleton3.getInstance();
        System.out.println(a1 == a2); //true
        System.out.println("a1.hashCode"+a1.hashCode());
        System.out.println("a2.hashCode"+a2.hashCode());
    }
}
class Singleton3 {
    private static Singleton3 instance;
    private Singleton3(){}
    //提供一个静态的公有方法，当使用到该方法时，才会创建instance
    public static Singleton3 getInstance(){
        if(instance == null){
            instance = new Singleton3();
        }
        return instance;
    }
}