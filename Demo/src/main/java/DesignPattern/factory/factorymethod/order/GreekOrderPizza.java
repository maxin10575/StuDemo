package DesignPattern.factory.factorymethod.order;

import DesignPattern.factory.factorymethod.pizza.CheesePizza;
import DesignPattern.factory.factorymethod.pizza.Pizza;

/**
 * @Author maxin
 * @Date 2019-11-25 15:17
 * @ClassName GreekOrderPizza
 * @Description
 * @Version 1.0
 **/
public class GreekOrderPizza extends OrderPizza{
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if(orderType.equals("greek")){
            pizza = new CheesePizza();
        }
        return pizza;
    }
}