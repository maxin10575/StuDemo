package com.epo.shuxinzj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnno {
	@Test
	public void testUser1() {
		try{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userservice = (UserService) context.getBean("userService");
		userservice.add();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
