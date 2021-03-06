package com.iocdi.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {
		
	@Test
	public void testUser() {
		//1加载spring配置文件，根据创建对象
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2得到配置创建的对象
		User user = (User)context.getBean("user");
		System.out.println(user);
		user.add();
	}
}
