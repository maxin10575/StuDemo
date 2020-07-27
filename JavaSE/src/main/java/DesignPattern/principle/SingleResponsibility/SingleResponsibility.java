package DesignPattern.principle.SingleResponsibility;

/**
 * @Author maxin
 * @Date 2019-11-11 23:23
 * @ClassName SingleResponsibility
 * @Description 单一职责
 * @Version 1.0
 **/
public class SingleResponsibility {
    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());
        a.depend2(new C());
    }

    interface inter1 {
        void operation1();
    }

    interface inter2 {
        void operation2();
    }

    static class B implements inter1 {

        @Override
        public void operation1() {
            System.out.println("B--1");
        }
    }

    static class C implements inter2 {

        @Override
        public void operation2() {
            System.out.println("C--2");
        }
    }

    static class A {
        public void depend1(inter1 i){
            i.operation1();
        }

        public void depend2(inter2 i){
            i.operation2();
        }
    }

}