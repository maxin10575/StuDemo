package DesignPattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: maxin
 * @create: 2023-02-09 10:25
 **/
public class CglibProxy {

    public static void main(String[] args) {
        //创建目标对象
        TeacherDao target = new TeacherDao();
        //获取到代理对象，并且将目标对象传递给代理对象
        TeacherDao proxyInstance = (TeacherDao) new ProxyFactory1(target).getProxyInstance();
        //执行代理对象的方法，触发 intecept 方法，从而实现 对目标对象的调用
        proxyInstance.teach();
    }
}

class ProxyFactory1 implements MethodInterceptor {
    //维护一个目标对象
    private Object target;
    //构造器，传入一个被代理的对象
    public ProxyFactory1(Object target) {
        this.target = target;
    }
    //返回一个代理对象: 是 target 对象的代理对象
    public Object getProxyInstance() {
        //1. 创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2. 设置父类
        enhancer.setSuperclass(target.getClass());
        // 3. 设置回调函数
        enhancer.setCallback(this);
        //4. 创建子类对象，即代理对象
        return enhancer.create();
    }
    //重写 intercept 方法，会调用目标对象的方法
    @Override
    public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
        System.out.println("Cglib 代理模式 ~~ 开始");
        Object returnVal = method.invoke(target, args);
        System.out.println("Cglib 代理模式 ~~ 提交");
        return returnVal;
    }
}