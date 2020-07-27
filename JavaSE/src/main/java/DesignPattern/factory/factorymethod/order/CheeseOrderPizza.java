package DesignPattern.factory.factorymethod.order;


import DesignPattern.factory.factorymethod.pizza.CheesePizza;
import DesignPattern.factory.factorymethod.pizza.Pizza;

/**
 * @Author maxin
 * @Date 2019-11-25 14:57
 * @ClassName CheeseOrderPizza
 * @Description
 * @Version 1.0
 **/
public class CheeseOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new CheesePizza();
        }
        return pizza;
    }
}