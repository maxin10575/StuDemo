package DesignPattern.factory.abstractmethod.pizza;

/**
 * @Author maxin
 * @Date 2019-11-19 21:04
 * @ClassName GreekPizza
 * @Description
 * @Version 1.0
 **/
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        setName("greek的pizza");
        System.out.println("给希腊披萨 准备原材料");
    }
}