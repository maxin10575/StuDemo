package DesignPattern.principle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @Author maxin
 * @Date 2019-11-07 22:22
 * @ClassName StartMainTest
 * @Description
 * @Version 1.0
 **/
public class StartMainTest {
    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Tank t = new Tank();
        TankTimeProxy ttp = new TankTimeProxy(t);
        TankLogProxy tlp = new TankLogProxy(ttp);  //在时间代理上再加上日志代理
        Moveable m = tlp;
        m.move();
    }
}

interface Moveable {
    void move();
    void stop();
}

class Tank implements Moveable {
    @Override
    public void move() {
        System.out.println("Tank moving 。。");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop() {
        System.out.println("Tank stop");
    }
}

class TankLogProxy implements Moveable {
    Moveable t;

    public TankLogProxy(Moveable t) {
        super();
        this.t = t;
    }
    @Override
    public void move() {
        System.out.println("Tank start");
        t.move(); //这里调用的其实是Tank的方法
        long end = System.currentTimeMillis();
        System.out.println("Tank end");
    }
    @Override
    public void stop() {
    }
}

class TankTimeProxy implements Moveable {
    Moveable t;
    public TankTimeProxy(Moveable t) {
        super();
        this.t = t;
    }
    @Override
    public void move() {
        Long start = System.currentTimeMillis();
        t.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    @Override
    public void stop() {
        Long start = System.currentTimeMillis();
        t.stop();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}


