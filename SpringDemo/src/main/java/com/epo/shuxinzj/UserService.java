package com.epo.shuxinzj;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//@Controller("userService")  δ����value�Ļ���context.getBean("userService"); userService Ĭ��Ϊclass ����ĸСд
@Controller
public class UserService {


	// �õ�dao����
	// 1 ����dao��������
	// ��dao��������ʹ��ע�� ��ɶ���ע��
//	@Autowired ��
	@Resource
	private UserDao userDao;

	// ʹ��ע�ⷽʽʱ����Ҫset����
	//  @Resource(name="userDao") name����ֵдע�ⴴ��dao����value��ֵ ��ʡ��


	public void add() {
		System.out.println("Service.....");
		userDao.add();
	}
}
