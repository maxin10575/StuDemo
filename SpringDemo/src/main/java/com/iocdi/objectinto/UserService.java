package com.iocdi.objectinto;

public class UserService {

    //1 ����dao��������
    private UserDao userDao;

    //2 ����set����
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service..........");
        userDao.add();
    }
    //��service����õ�dao����󣬲��ܵ���dao����ķ���
//		UserDao dao = new UserDao ();
//		dao.add();
}
