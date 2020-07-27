package DesignPattern.principle;

/**
 * @Author maxin
 * @Date 2019-11-18 16:27
 * @ClassName RegulationBetweenClasses
 * @Description
 * @Version 1.0
 **/
public class RegulationBetweenClasses {
    private A a; //聚合
    private B b  = new B(); //6.组合 : 整体与部分的关系，但是整体与部分不可分开。是 关联关系的特例
    //如果程序RegulationBetweenClasses实体中定义了对A进行级联删除，即删除RegulationBetweenClasses时
    //连同A一起删除，那么A和RegulationBetweenClasses就是组合了。
}

class A {}

class B {}

//5.聚合关系（Aggregation）：表示整体与部分的关系，整体和部分可以分开。聚合关系是 关联关系的特例
                           //所以他具有关联的导航性和多重性。如一台电脑，可以和配件分离开。

//4.关联关系（Association）：类与类之间的联系，是 依赖关系的特例。
                           //具有导航性：即双向关系或单向关系
//单向一对一关系：
class C{ private D d;}

class D{}

//双向一对一关系
class E{ private F f;}

class F{ private E e;}

//3.实现关系（Implementation）：实际上就是A类实现B接口，是 依赖关系的特例

//2.泛化关系（generalization）：就是继承关系，是 依赖关系的特例

//1.依赖关系：只要是在类中用到了对方，那么存在依赖关系。
           //存在形式：类中用到了对方；是类的成员属性；是方法的返回类型；方法接收的参数类型；方法中使用到