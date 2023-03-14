package DesignPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author maxin
 * @Date 2019-12-10 15:45
 * @ClassName Test
 * @Description JDK自带动态代理
 * @Version 1.0
 **/
public class JDKProxyTest {
    public static void main(String[] args) {
        //1. 静态代理
//        ITeacherDao  iTeacherDao = new TeacherDao();
//        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(iTeacherDao);
//        teacherDaoProxy.teach();
        //2.JDK
        //创建目标对象
        ITeacherDao target = new TeacherDao();
        //给目标对象，创建代理对象, 可以转成 ITeacherDao
        ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(target).getProxyInstance();
        // proxyInstance=class com.sun.proxy.$Proxy0 内存中动态生成了代理对象
        System.out.println("proxyInstance=" + proxyInstance.getClass());
        //通过代理对象，调用目标对象的方法 //proxyInstance.teach();
        proxyInstance.sayHello(" tom ");
        System.out.println("proxyInstance2222=" + proxyInstance.getClass());
        proxyInstance.teach();
    }
}

interface ITeacherDao {
    void teach(); // 授课方法

    void sayHello(String name);
}

class ProxyFactory {
    //维护一个目标对象 , Object
    private Object target;

    //构造器 ， 对 target 进行初始化
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象 生成一个代理对象
    public Object getProxyInstance() {
        //1. ClassLoader loader : 指定当前目标对象使用的类加载器, 获取加载器的方法固定
        //2. Class<?>[] interfaces: 目标对象实现的接口类型，使用泛型方法确认类型
        //3. InvocationHandler h : 事情处理，执行目标对象的方法时，会触发事情处理器方法, 会把当前执行的目标对象方法作为参数传入
        //    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK 代理开始~~"); //反射机制调用目标对象的方法
                        Object returnVal = method.invoke(target, args);
                        System.out.println("JDK 代理提交");
                        return returnVal;
                    }
                });
    }
}

//1. 静态代理
class TeacherDaoProxy implements ITeacherDao {
    private ITeacherDao iTeacherDao;

    TeacherDaoProxy(ITeacherDao iTeacherDao) {
        this.iTeacherDao = iTeacherDao;
    }

    @Override
    public void teach() {
        System.out.println("开始静态代理，完成一些操作");
        iTeacherDao.teach();
        System.out.println("代理对象，完成后续一些操作");
    }

    @Override
    public void sayHello(String name) {
    }
}

class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("被代理对象运行teach.... ");

        return;
    }

    @Override
    public void sayHello(String name) {
        System.out.println("被代理对象运行hello ：" + name);
    }
}