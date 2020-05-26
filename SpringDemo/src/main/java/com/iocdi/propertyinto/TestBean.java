package com.iocdi.propertyinto;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
		
	@Test
	public void testUser() {
		//1加载spring配置文件，根据创建对象
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2得到配置创建的对象
		Book2 demo = (Book2)context.getBean("demo");
		demo.test1();

				Book book = (Book)context.getBean("book");
				book.demobook();
	}
}
