package com.iocdi.propertyinto;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
		
	@Test
	public void testUser() {
		//1����spring�����ļ������ݴ�������
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2�õ����ô����Ķ���
		Book2 demo = (Book2)context.getBean("demo");
		demo.test1();

				Book book = (Book)context.getBean("book");
				book.demobook();
	}
}
