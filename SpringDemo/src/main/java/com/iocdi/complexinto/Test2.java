package com.iocdi.complexinto;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
		
	@Test
	public void testUser() {
		/*//1加载spring配置文件，根据创建对象
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2得到配置创建的对象
		propertyDemo1 demo = (propertyDemo1)context.getBean("demo");
		demo.test1();*/
		
		//1加载spring配置文件，根据创建对象
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextioc.xml");
				//2得到配置创建的对象
				PersonT demo = (PersonT)context.getBean("PersonT");
				demo.test3();
	}
}
