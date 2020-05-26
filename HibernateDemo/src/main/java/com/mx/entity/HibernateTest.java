package com.mx.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void testAdd() {
        // 1.����hibernate���������ļ�
        // ��src�����ҵ�������hibernate.cfg.xml
        // ��hibernate�����װ����
        Configuration cfg = new Configuration();
        cfg.configure();
        // 2.����SessionFactory����
        // ��ȡhibernate���������ļ����ݣ�����sessionFactory
        // �ڹ����У�����ӳ���ϵ�����������ݿ�����ɱ���
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        // 3.ʹ��SessionFactory����session����
        // ��������
        Session session = sessionFactory.openSession();
        // 4.��������
        Transaction tx = session.beginTransaction();
        // 5.д�����߼�crud����
        User user = new User();
        //�˴�����idֵ������Ч��idֵ�����ݿ���Զ�����
//        user.setMyid("2");
        user.setMyname("С��");
        user.setMypwd("111");
        user.setMyaddr("China");
        // ����session�ķ���ʵ�����
        session.save(user);
        // 6.�ύ����
        tx.commit();
        // 7.�ر���Դ
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testGet() {
        //����淶����
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            //����id��ѯ
            //����session�����get����
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

        //�뱾���̰߳󶨵�session

        Session session = HibernateUtils.getSessionobject();
        //��������
        Transaction tx = session.beginTransaction();
        //1����idֵ��ѯ
        User user = session.get(User.class, 1);
        //2�򷵻ص�user�������������޸ĺ��ֵ
        user.setMyname("����");
        //3����session��update����
        session.update(user);
        System.out.println("user==="+user);
        //�ύ����
        tx.commit();
        //�뱾���̰߳󶨵�session ����Ҫ�ֶ��رգ���Ȼ�ᱨ��Session was already closed
//        session.close();
    }

    @Test
    public void tesDelete() {
        //�������ȡsession
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        //��������
        Transaction tx = session.beginTransaction();
        //��һ�ַ���
        User user = session.get(User.class, 5);
        session.delete(user);
        //�ڶ��ַ���
/*        User user = new User();
        user.setMyid(6);
        session.delete(user);*/
        //�ύ����
        tx.commit();
        //�ر�
        session.close();
    }

    @Test
    public void testSaveOrUpdate() {
        //�������ȡsession
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        //��������
        Transaction tx = session.beginTransaction();

        // ʵ���������˲ʱ̬�������
      /*  User user = new User();
        user.setMyname("����");
        user.setMypwd("222");
        user.setMyaddr("China");
        session.saveOrUpdate(user);*/

       /* ʵ����������й�̬�����޸�
       User user1 = new User();
        user1.setMyid(6);
        user1.setMyname("����222");
        user1.setMypwd("222");
        user1.setMyaddr("China222");
        session.saveOrUpdate(user1);*/


        // ʵ��������ǳ־�̬�����޸�
        User user = session.get(User.class, 6);
        user.setMyname("����");
        session.saveOrUpdate(user);

        //�ύ����
        tx.commit();
        //�ر�
        session.close();
        sessionFactory.close();
    }

}















