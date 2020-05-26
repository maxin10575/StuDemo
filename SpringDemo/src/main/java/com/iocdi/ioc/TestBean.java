package com.iocdi.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
	
	@Test
	public void testBean() {
		//1����spring�����ļ������ݴ�������
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2�õ����ô����Ķ���
		Bean2 bean2 = (Bean2) context.getBean("bean2");
		System.out.println(bean2);
		bean2.add();
		
		//1����spring�����ļ������ݴ�������
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2�õ����ô����Ķ���
		Bean3 bean3 = (Bean3) context.getBean("bean_3");
		System.out.println(bean3);
	}
}
