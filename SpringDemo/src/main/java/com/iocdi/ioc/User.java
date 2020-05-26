package com.iocdi.ioc;

public class User {
	
//默认存在无参构造器。如果写了有参构造器。则也需要手动写无参构造器
/*private User(){
	System.out.println("add111.....");
}
private User(String a ){
	System.out.println("add2.....");
	}*/
	
	public void add() {
		System.out.println("add.....");
	}
	
	public static void main(String[] args) {
		//原始做法
//		User user = new User();
//		user.add();
	}
}
