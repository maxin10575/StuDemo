package DesignPattern.principle.DependencyReverse;

/**
 * @Author maxin
 * @Date 2019-11-12 10:22
 * @ClassName DependencyReverse
 * @Description 依赖倒转原则：1.底层模块尽量都要有抽象类或接口，或者两者都有，程序稳定性更好。
 * @Version 1.0             2.变量的声明类型尽量是抽象类或接口，这样我们的变量引用和实际对象间，
 *                              就存在一个缓冲层，利于程序扩展和优化
 *                          3.继承时遵循里氏替换原则
 **/
public class DependencyReverse {

    public static void main(String[] args ) {
        //1
        Person1 person1 = new Person1();
        person1.receive(new Email1());
        //2
        Person person = new Person();
        person.receive(new Email());
        person.receive(new WeChat());
    }

    interface IReceiver {
        public String getInfo();
    }

    static class Email implements IReceiver{
        @Override
        public String getInfo() {
            return "Email---";
        }
    }

    static class WeChat implements IReceiver{
        @Override
        public String getInfo() {
            return "WeChat---";
        }
    }

    static class Person {
        //对接口依赖
        public void receive(IReceiver iReceiver){
            System.out.println(iReceiver.getInfo());
        }
    }

    //方式1：传统
    //1.如果要接收对象是 微信，短信等，则要新增类，同事Person也要增加相应的接收方法
    //解决思路：引入一个抽象的接口IReceiver，表示接收者，这样Person类与接口IReceiver发送依赖
    // 因为Email,weixin，等属于接收的范围，它们各自实现IReceiver接口即可
    static class Person1 {
        public void receive(Email1 email1){
            System.out.println(email1.getInfo());
        }
    }
    static class Email1{
        public String getInfo() {
            return "Email----11";
        }
    }
}