package com.epo.zhujie;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component("user")  //�൱��<bean id ="user" class=""/>
public class User {
	
	public void add() {
		System.out.println("add............");
	}
}
