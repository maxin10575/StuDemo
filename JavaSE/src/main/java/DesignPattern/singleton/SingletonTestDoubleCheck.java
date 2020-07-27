package DesignPattern.singleton;

/**
 * @Author maxin
 * @Date 2019-11-19 10:30
 * @ClassName SIngletonTestDoubleCheck
 * @Description 双重检查：
 *              1.Double-Check概念是读线程开发中常使用到的进行两次if(singleton==null)
 *                  检查，就能保证线程安全
 *              2.实例代码只用执行一次，后面的再次访问，判断if(singleton ==null)，直接
 *                  return实例化对象，也避免反复进行方法同步
 *              3.线程安全：延迟加载，效率较高
 *             结论：实际开发中，推荐使用
 * @Version 1.0
 **/
public class SingletonTestDoubleCheck {
}

class SingletonD {  //使用volatile 修饰，防止出现内部成员变量空指针异常
    private static volatile SingletonD instance;
    private SingletonD(){}
    public static SingletonD getInstance() {
        if(instance == null){
            synchronized (SingletonD.class){
                if (instance == null){
                    instance = new SingletonD();
                }
            }
        }
        return instance;
    }
}