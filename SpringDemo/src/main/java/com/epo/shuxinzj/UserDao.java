package com.epo.shuxinzj;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Service("userDao")
//@Service
@Controller
//@Repository
//@Component
public class UserDao {
	public void add() {
		System.out.println("UserDaoMethod......");
	}
}
