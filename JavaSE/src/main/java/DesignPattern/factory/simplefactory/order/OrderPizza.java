package DesignPattern.factory.simplefactory.order;

import DesignPattern.factory.simplefactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author maxin
 * @Date 2019-11-19 21:06
 * @ClassName OrderPizza
 * @Description 1.优点：好理解，简单易操作
 *              2.缺点：违背了ocp原则，即对扩展开放，对修改关闭。即给类增加新功能时，尽量少修改代码或不修改代码。
 * @Version 1.0
 **/
public class OrderPizza {
/*
    //构造器
    public OrderPizza() {
        Pizza pizza = null;
        String orderType;//订购披萨的类型
        do {
            orderType = getType();
            if (orderType.equals("greek")) {
                pizza = new GreekPizza();
                pizza.setName("希腊披萨");
            } else if (orderType.equals("cheese")) {
                pizza = new CheesePizza();
                pizza.setName("奶酪披萨");
            } else {
                break;
            }
            //输出pizza制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

        } while (true);

    } */

    //2.定义一个简单工厂对象
    SimpleFactory simpleFactory;
    Pizza pizza = null;

    //构造器
    public OrderPizza(SimpleFactory simpleFactory) {
        setFactory(simpleFactory);
    }

    public void setFactory(SimpleFactory simpleFactory) {
        String orderType = ""; //用户输入的
//        this.simpleFactory = simpleFactory;
        do{
            orderType = getType();
            pizza = simpleFactory.createPizza(orderType);
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