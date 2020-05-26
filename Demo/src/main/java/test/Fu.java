package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//子类是不继承父类的构造器（构造方法或者构造函数）的，它只是调用（隐式或显式）。
// 如果父类的构造器带有参数，则必须在子类的构造器中显式地通过 super 关键字调用父
// 类的构造器并配以适当的参数列表。
//如果父类构造器没有参数，则在子类的构造器中不需要使用 super 关键字调用父类构造器，
// 系统会自动调用父类的无参构造器。
public  class Fu {
	int a ;
	public Fu(int a ){
		System.out.println("Fu的有参构造器"+a);
	}
//	public Fu(){
//		System.out.println("Fu的无参构造器");
//	}



}
