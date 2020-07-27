package DesignPattern.factory.simplefactory.order;

import DesignPattern.factory.simplefactory.pizza.CheesePizza;
import DesignPattern.factory.simplefactory.pizza.GreekPizza;
import DesignPattern.factory.simplefactory.pizza.Pizza;

/**
 * @Author maxin
 * @Date 2019-11-19 21:43
 * @ClassName SimpleFactory
 * @Description 简单工厂模式
 *                  1.属于创建型模式，是工厂模式的一种。是由一个工厂对象决定创建出哪一种产品类的实例。
 *                  2.定义了一个创建对象的类，由这个类来封装实例化对象的行为（代码）
 *                  3.在软开中，用到大量的创建某种，某类或者某批对象时，就会使用到工厂模式。
 * @Version 1。0
 **/
public class SimpleFactory {
    //根据orderType 返回对应的pizza对象
    //  public Pizza createPizza(String orderType)
    public static Pizza createPizza(String orderType) {//改成静态
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        }
        return pizza;
    }
}