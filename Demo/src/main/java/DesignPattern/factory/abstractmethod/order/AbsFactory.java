package DesignPattern.factory.abstractmethod.order;

import java.util.Calendar;

/**
 * @Author maxin
 * @Date 2019-11-25 16:14
 * @ClassName abstractmethod
 * @Description 抽象工厂模式：1.定义了一个interface用于创建相关或有依赖关系的对象簇，而无需指明具体的类。
 *                          2.可以将简单工厂模式和工厂方法模式进行整合
 *                          3.从设计层面看，抽象工厂模式就是对简单工厂模式的改进，（或者称为进一步的抽象）
 *                          4.将工厂抽象成两层，Absfactory(抽象工厂）和具体实现的工厂子类。可根据创建对象
 *                          类型使用对应的工厂子类。这样将单个的简单工厂类变成了工厂处簇，更利于代码的维护扩展。
 * @Version 1.0
 **/
public abstract class AbsFactory {
    Calendar calendar;
    StringBuilder stringBuilder  = new StringBuilder("1");
    public static void main(String[] args) { //获取折叠式手机 (样式 + 品牌 )
        Phone phone1 = new FoldedPhone(new XiaoMi());
        phone1.open(); phone1.call(); phone1.close();
        System.out.println("======================="); Phone phone2 = new FoldedPhone(new Vivo()); phone2.open();
        phone2.call(); phone2.close();
        System.out.println("==============");
        UpRightPhone phone3 = new UpRightPhone(new XiaoMi());
        phone3.open(); phone3.call(); phone3.close();
        System.out.println("==============");
        UpRightPhone phone4 = new UpRightPhone(new Vivo());
        phone4.open(); phone4.call(); phone4.close();
    }
}

 interface Brand {
    void open(); void close(); void call();
}
 abstract class Phone {
     //组合品牌
     private Brand brand;
     //构造器
     public Phone(Brand brand) {
         super();
         this.brand = brand; }
     protected void open() { this.brand.open(); }
     protected void close() {brand.close(); }
     protected void call() { brand.call(); } }

 class FoldedPhone extends Phone {
    //构造器
    public FoldedPhone(Brand brand) {
        super(brand); }
    public void open() { super.open();
        System.out.println(" 折叠样式手机 "); }
    public void close() { super.close();
        System.out.println(" 折叠样式手机 "); }
    public void call() { super.call();
        System.out.println(" 折叠样式手机 "); }
}
 class UpRightPhone extends Phone {
    //构造器
    public UpRightPhone(Brand brand) {
        super(brand); }
    public void open() { super.open();
        System.out.println(" 直立样式手机 "); }
    public void close() { super.close();
        System.out.println(" 直立样式手机 "); }
    public void call() { super.call();
        System.out.println(" 直立样式手机 "); }
}
class Vivo implements Brand {
    @Override
    public void open() { }
    @Override
    public void close() {
        System.out.println(" Vivo 手机关机 "); }
    @Override
    public void call() {
        System.out.println(" Vivo 手机打电话 "); }
}

class XiaoMi implements Brand {
    @Override
    public void open() {
        System.out.println(" 小米手机开机 "); }
    @Override
    public void close() {
        System.out.println(" 小米手机关机 "); }
    @Override
    public void call() {
        System.out.println(" 小米手机打电话 "); }
}