package com.mx.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void testAdd() {
        // 1.加载hibernate核心配置文件
        // 到src下面找到名称是hibernate.cfg.xml
        // 在hibernate里面封装对象
        Configuration cfg = new Configuration();
        cfg.configure();
        // 2.创建SessionFactory对象
        // 读取hibernate核心配置文件内容，创建sessionFactory
        // 在过程中，根据映射关系，在配置数据库里面吧表创建
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        // 3.使用SessionFactory创建session对象
        // 类似连接
        Session session = sessionFactory.openSession();
        // 4.开启事务
        Transaction tx = session.beginTransaction();
        // 5.写具体逻辑crud操作
        User user = new User();
        //此处设置id值不会生效，id值在数据库会自动增长
//        user.setMyid("2");
        user.setMyname("小王");
        user.setMypwd("111");
        user.setMyaddr("China");
        // 调用session的方法实现添加
        session.save(user);
        // 6.提交事务
        tx.commit();
        // 7.关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testGet() {
        //事务规范代码
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            //根据id查询
            //调用session里面的get方法
            User user = session.get(User.class, 1);
            System.out.println("user==="+user);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }

    }

    @Test
    public void tesUpdate() {

        //与本地线程绑定的session

        Session session = HibernateUtils.getSessionobject();
        //开启事务
        Transaction tx = session.beginTransaction();
        //1根据id值查询
        User user = session.get(User.class, 1);
        //2向返回的user对象里面设置修改后的值
        user.setMyname("张四");
        //3调用session的update方法
        session.update(user);
        System.out.println("user==="+user);
        //提交事务
        tx.commit();
        //与本地线程绑定的session 不需要手动关闭，不然会报错：Session was already closed
//        session.close();
    }

    @Test
    public void tesDelete() {
        //工具类获取session
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction tx = session.beginTransaction();
        //第一种方法
        User user = session.get(User.class, 5);
        session.delete(user);
        //第二种方法
/*        User user = new User();
        user.setMyid(6);
        session.delete(user);*/
        //提交事务
        tx.commit();
        //关闭
        session.close();
    }

    @Test
    public void testSaveOrUpdate() {
        //工具类获取session
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction tx = session.beginTransaction();

        // 实体类对象是瞬时态，做添加
      /*  User user = new User();
        user.setMyname("王五");
        user.setMypwd("222");
        user.setMyaddr("China");
        session.saveOrUpdate(user);*/

       /* 实体类对象是托管态，做修改
       User user1 = new User();
        user1.setMyid(6);
        user1.setMyname("王五222");
        user1.setMypwd("222");
        user1.setMyaddr("China222");
        session.saveOrUpdate(user1);*/


        // 实体类对象是持久态，做修改
        User user = session.get(User.class, 6);
        user.setMyname("哈哈");
        session.saveOrUpdate(user);

        //提交事务
        tx.commit();
        //关闭
        session.close();
        sessionFactory.close();
    }

}















