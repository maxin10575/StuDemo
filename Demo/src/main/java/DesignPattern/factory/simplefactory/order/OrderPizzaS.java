package DesignPattern.factory.simplefactory.order;

import DesignPattern.factory.simplefactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author maxin
 * @Date 2019-11-20 09:50
 * @ClassName OrderPizzaStaticFactory
 * @Description 静态
 * @Version 1.0
 **/
public class OrderPizzaS {
    Pizza pizza = null;
    String orderType = "";

    //构造器
    public OrderPizzaS() {
        do{
            orderType = getType();
            pizza = SimpleFactory.createPizza(orderType);
            //输出pizza
            if(pizza != null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购披萨失败");
                break;
            }
        }while (true);
    }

    //写一个可以动态获取客户希望订购的披萨种类
    private String getType() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(" input pizza 种类 :");
            String instr = bufferedReader.readLine();
            return instr;
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}