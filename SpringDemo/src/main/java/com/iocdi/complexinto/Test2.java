package com.iocdi.complexinto;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
		
	@Test
	public void testUser() {
		/*//1����spring�����ļ������ݴ�������
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2�õ����ô����Ķ���
		propertyDemo1 demo = (propertyDemo1)context.getBean("demo");
		demo.test1();*/
		
		//1����spring�����ļ������ݴ�������
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextioc.xml");
				//2�õ����ô����Ķ���
				PersonT demo = (PersonT)context.getBean("PersonT");
				demo.test3();
	}
}
