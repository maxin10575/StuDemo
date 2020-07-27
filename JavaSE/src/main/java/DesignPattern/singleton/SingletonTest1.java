package DesignPattern.singleton;

/**
 * @Author maxin
 * @Date 2019-11-18 17:34
 * @ClassName Singleton
 * @Description//
 *  饿汉式（静态变量）
 * /**
 *  * 1.优点：写法简单，类装载时候完成实例化，避免了线程同步问题。
 *  * 2.缺点：在类装载时候就完成实例化，没有达到lazy loading效果。
 *  *        若从始至终未使用这个实例，则会造成内存浪费
 *  * 3.这种方式基于classloader机制避免了多线程同步问题，不过，instance在类装载时就实例化。
 *  *   在单例模式中大多数都是调用getInstance方法，但是导致类装载的原因有很多，因此不能确定有其他
 *  *   的方式（或者其他的静态方法）导致类装载，这时候初始化instance就么达到lazy loading效果
 *  * 4.结论：这种单例模式可用，可能 造成内存浪费
 *
 * @Version 1.0
 **/
public class SingletonTest1 {
    public static void main(String[] args) {
        Singleton a1 = Singleton.getInstance();
        Singleton a2 = Singleton.getInstance();
        System.out.println(a1 == a2); //true
        System.out.println("a1.hashCode"+a1.hashCode());
        System.out.println("a2.hashCode"+a2.hashCode());
    }
}
class Singleton {
    //1.构造器私有化，防止外部能new
    private Singleton(){}
   //2.本类内部创建对象实例
    private final static Singleton instance = new Singleton();
    //3.提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance(){
        return instance;
    }
}