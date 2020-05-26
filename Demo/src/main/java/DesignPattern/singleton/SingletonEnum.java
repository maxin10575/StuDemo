package DesignPattern.singleton;

/**
 * @Author maxin
 * @Date 2019-11-19 15:27
 * @ClassName SingletonEnum
 * @Description 1.借助JDK1.5中添加的枚举来实现单例模式，可避免多线程同步问题，
 *                且还能防止反序列化重新创建新的对象
 *              2.推荐使用
 * @Version 1.0
 **/
public class SingletonEnum {
    public static void main(String[] args) {
        Singleton5 singletonEnum = Singleton5.INSTANCE;
        Singleton5 singletonEnum2 = Singleton5.INSTANCE;
        System.out.println(singletonEnum == singletonEnum2);//true hashCode相同
        singletonEnum.testenum();
    }
}

enum Singleton5 {
    INSTANCE;
    public void testenum(){
        System.out.println("enum--singleton");
    }
}