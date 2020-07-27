package DesignPattern.factory.factorymethod.order;
import DesignPattern.factory.factorymethod.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author maxin
 * @Date 2019-11-25 14:45
 * @ClassName OrderPizza
 * @Description
 * @Version 1.0
 **/
public abstract class OrderPizza {
    //构造器
    public OrderPizza() {
        Pizza pizza = null;
        String orderType;//订购披萨的类型
        do {
            orderType = getType();
            pizza =  createPizza(orderType);//抽象方法，由工厂子类完成
            //输出pizza制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    //定义一个抽象方法，让各个工厂子类自己实现
    abstract Pizza createPizza(String orderType);

    //写一个可以动态获取客户希望订购的披萨种类
    private String getType() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(" input pizza 种类 :");
            String instr = bufferedReader.readLine();
            return instr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}