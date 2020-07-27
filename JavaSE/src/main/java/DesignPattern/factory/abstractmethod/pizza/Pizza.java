package DesignPattern.factory.abstractmethod.pizza;

/**
 * @Author maxin
 * @Date 2019-11-19 20:57
 * @ClassName Pizza
 * @Description
 * @Version 1.0
 **/
public abstract class Pizza {
     String name;
    public abstract void prepare();
    public void bake() {
        System.out.println(name + " baking;");
    }
    public void cut() {
        System.out.println(name + " cutting;");
    }
    public void box() {
        System.out.println(name + " boxing;");
    }
    public void setName(String name) {
        this.name = name;
    }
}