package DesignPattern.factory.simplefactory.order;

/**
 * @Author maxin
 * @Date 2019-11-19 21:13
 * @ClassName PizzaStore
 * @Description 相当于一个客户端,发出订购
 * @Version 1.0
 **/
public class PizzaStore {
    public static void main(String[] args) {
//        new OrderPizza();
        //使用简单工厂模式
//        new OrderPizza(new SimpleFactory());
        new OrderPizzaS();
        System.out.println("退出程序");
    }
}
