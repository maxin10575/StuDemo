package com.epo.zhujie;

import org.springframework.stereotype.Component;

@Component("user")  //�൱��<bean id ="user" class=""/>
public class User {
	
	public void add() {
		System.out.println("add............");
	}
}
