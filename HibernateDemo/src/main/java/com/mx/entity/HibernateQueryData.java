package com.mx.entity;

import org.hibernate.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateQueryData {

    //使用query对象
    @Test
    public void tesQuery() {
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtils.getSessionobject();
            tx = session.beginTransaction();
            //1 . 创建Query对象
            //方法里面写hql语句 form后写实体类名称
            Query query = session.createQuery("from User");
           //2 调用query对象里面的方法得到结果s
            List<User> list = query.list();
            for (User user : list) {
              System.out.println("user==="+user);
            }
             tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
        }
    }

    //使用Criteria对象
    @Test
    public void testCriteria() {    
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtils.getSessionobject();
            tx = session.beginTransaction();
            //1 . 创建Criteria对象
            //方法里面的参数是实体类class
            Criteria criteria = session.createCriteria(User.class);
            //调用方法得到结果
            List<User> list = criteria.list();
            for (User user : list) {
                System.out.println("user==="+user);
            }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
        }
    }

    //使用SQLQuery对象
    @Test
    public void testSQLQuery() {
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtils.getSessionobject();
            tx = session.beginTransaction();


            //1 . 创建SQLQuery对象  //返回的是数组
            SQLQuery sqlQuery = session.createSQLQuery("select * from hibernate_user");

            //2 调用方法得到结果
         /*   List<Object[]> list = sqlQuery.list();
            for (Object[] objects : list) {
                System.out.println("user==="+ Arrays.toString(objects));
            }*/

            //返回的list中每部分是对象
            sqlQuery.addEntity(User.class);
            List<User> list = sqlQuery.list();
            for (User user : list) {
                System.out.println("user==="+user);
            }


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
        }
    }

}
