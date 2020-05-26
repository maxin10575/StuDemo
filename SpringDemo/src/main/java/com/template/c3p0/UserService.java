package com.template.c3p0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("userService")
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void add() {
		userDao.add();
	}
	
	
	
}
