package com.epo.shuxinzj;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//@Controller("userService")  未定义value的话，context.getBean("userService"); userService 默认为class 首字母小写
@Controller
public class UserService {


	// 得到dao对象
	// 1 定义dao类型属性
	// 在dao属性上面使用注解 完成对象注入
//	@Autowired 或
	@Resource
	private UserDao userDao;

	// 使用注解方式时候不需要set方法
	//  @Resource(name="userDao") name属性值写注解创建dao对象value的值 可省略


	public void add() {
		System.out.println("Service.....");
		userDao.add();
	}
}
