package DesignPattern.singleton;

/**
 * @Author maxin
 * @Date 2019-11-18 17:48
 * @ClassName Singleton2
 * @Description 饿汉式（静态代码块）：
 *              同静态变量饿汉式，只不过将类实例化的过程放在了静态代码块中，也是在类装载的时候，就
 *              执行静态代码块中的代码，初始化类的实例，优缺点同上。
 * @Version 1.0
 **/
public class SingletonTest2 {
    public static void main(String[] args) {
        Singleton2 a1 = Singleton2.getInstance();
        Singleton2 a2 = Singleton2.getInstance();
        System.out.println(a1 == a2); //true
        System.out.println("a1.hashCode"+a1.hashCode());
        System.out.println("a2.hashCode"+a2.hashCode());
    }
}
class Singleton2 {
    //1.构造器私有化，外部能new
    private Singleton2() {}
    //2.本类内部创建对象实例
    private static Singleton2 instance;
    static { //静态代码块中，创建单例对象
        instance = new Singleton2();
    }
    //3.提供一个公有的静态方法，返回实例对象
    public static Singleton2 getInstance() {
        return instance;
    }
}