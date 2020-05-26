package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/*
 * 动态获取类中的方法信息
 */
public class ReflectDemo {
	public static void main(String[] args) throws Exception {
		String className = "reflect.Foo";
		//动态加载类：
		Class cls = Class.forName(className);
		//如果没有无参构造器，将抛出异常。
		Object obj = cls.newInstance();
//		返回某个类的所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法。
//		Method[] methods = cls.getMethods()
//      getDeclaredMethods()对象表示的类或接口声明的所有方法,包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
//		 当然也包括它所实现接口的方法
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			//执行有带有 annoTest 注解的方法
			annoTest ann = 	method.getAnnotation(annoTest.class);
			System.out.println("*******"+ann);
			if(ann != null){
				method.invoke(obj);
			}

			String name = method.getName();
			Class[] types = method.getParameterTypes();
//			System.out.println("methodname: "+name+  "    parameter:  " +Arrays.toString(types));
			//检查参数类型列表长度
//			if(types.length!=0){
//				continue;
//			}
//			if(name.startsWith("test")){
//				method.invoke(obj);
//			}
//			System.out.println("method=="+method);
		}

		//设置属性值   方法1
		//方法可以使用
		Field field = cls.getDeclaredField("book");
		//setAccessible  属性若是私有的 需要设置为 true
		field.setAccessible(true);
		field.set(obj,100);
//		Object val = field.get(obj);
//		System.out.println(val);

		String name = "who";
		//int.class 表示int类型
		Class[] types = {String.class, int.class};
		Class[] types2 = {Integer.class};
		Class[] types3 = {String.class};
		Class[] types1 = {};
		Method method1 = cls.getDeclaredMethod(name, types1);
		Method method2 = cls.getDeclaredMethod(name, types2);
		Method method3 = cls.getDeclaredMethod("who1", types2);
		//执行方法
		Object val = method1.invoke(obj);
		//执行带参数方法  设置属性值2
		Object val2 = method2.invoke(obj, new Object[]{2});
		System.out.println("val2===" + val2);
		//执行带参数方法 并有返回值
		Object val3 = method3.invoke(obj, new Object[]{3});
		System.out.println("val3=====" + val3);
	}

	 public void xmlread(){
		 ApplicationContext ctx = null;
		 try {
			 ctx = new ApplicationContext("spring.xml");
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 Date d = (Date)ctx.getBean("date");
		 System.out.println(d);
		 Goo goo = (Goo)ctx.getBean("goo");
		 System.out.println(goo);
	 }
}
