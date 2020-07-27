package DesignPattern.factory.factorymethod.pizza;

/**
 * @Author maxin
 * @Date 2019-11-19 21:01
 * @ClassName CheesePizza
 * @Description
 * @Version 1.0
 **/
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("cheese的pizza");
        System.out.println("给制作奶酪pizza 准备原材料");
    }
}