package com.iocdi.pnamespaceinto;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {

	@Test
	public void testUser() {

		// 1????spring????????????????????
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2???????????????
		Person demo = (Person) context.getBean("person");
		demo.test2();
	}
}
