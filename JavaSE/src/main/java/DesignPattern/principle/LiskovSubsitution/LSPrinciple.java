package DesignPattern.principle.LiskovSubsitution;

/**
 * @Author maxin
 * @Date 2019-11-13 10:43
 * @ClassName LSPrinciple
 * @Description 里氏替换原则 1.所有引用基类的地方必须能透明地使用其子类的对象
 *                          2.子类中尽量不要重写父类的方法
 *                          3.继承实际上让两个类耦合性增强了。在适当情况下，
 *                          可以通过聚合，组合，依赖来解决问题。
 *
 * @Version 1.0
 **/
public class LSPrinciple {
    public static void main(String[] args) {
        //1
        A a = new A();
        System.out.println(a.func1(11,3));
        B b = new B();
        System.out.println(b.func1(11,3));
    //2 因为B类不再继承A类，因此调用者不会再认为func1是求减法 调用完成的功能就会很明确
        D d = new D();
        d.func1(11,3);   //这里本意是求出11+3
    //使用组合仍然可以使用到A类相关的方法
        d.func3(11,3);
    }

}

//1 违反里氏替换原则
class A {
    public int func1(int num1,int num2){
        return num1 - num2;
    }
}

class B extends A {
    public int func1(int a,int b){
        return a+b;
    }
    public int func2(int a,int b) {
        return a*b;
    }
}

//2 符合里氏替换原则
class base {   //把更加基础的方法和成员写到Base类
}

class C extends base {
    public int func1(int num1,int num2){
        return num1 - num2;
    }
}

class D extends base {
    private C c = new C();
    //仍然使用A的方法 使用组合关系
    public int func3(int a,int b){
        return this.c.func1(a,b);
    }
    //这里重写了A类的方法，可能是无意识
    public int func1(int a, int b){
        return a+b;
}

    public int func2(int a,int b) {
        return a*b;
    }
}