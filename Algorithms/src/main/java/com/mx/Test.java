package com.mx;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: StuDemo
 * @description: 递归测试
 * @author:
 * @create: 2020-07-29 15:28
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {
    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,-2);
        int a = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(a);
    }
 /*       Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.add(Calendar.MONTH, -1);
        int lastMonthMaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
        String starTimeStr = sdf.format(c.getTime()); //上月最后一天
        Date startDate = sdf.parse(starTimeStr);
        long startTime = startDate.getTime();
        System.out.println(sdf.format(startTime));
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        String endTimeStr = sdf2.format(c.getTime()); //上月第一天
        Date endDate = sdf.parse(endTimeStr);
        long endtime = endDate.getTime();
        System.out.println(sdf.format(endtime));*/
/*
 List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setAge("1");
        user1.setName("小明");
        user1.setIndex(2.0);
        User user2= new User();
        user2.setIndex(3.2);
        user2.setAge("2");
        user2.setName("小白");
        User user3 = new User();
        user3.setAge("3");
        user3.setName("小黑");
        user3.setIndex(4.2);
        User user4 = new User();
        user4.setAge("2");
        user4.setName("小红");
        user4.setIndex(6.2);
        User user5 = new User();
        user5.setAge("1");
        user5.setName("小1");
        user5.setIndex(2.2);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        List<String> lageList = list.stream().map(User :: getAge).distinct().collect(Collectors.toList());
        lageList.forEach(System.out::println);
        lageList.stream().forEach(age ->{
            double passEfficIndex = list.stream().filter(user -> age.equals(user.getAge())).mapToDouble(User::getAge).average();
        });

    }
*/

/*
    public static long fun1(long n) {
        if (n == 1) {
            return 1;
        }
        return n * fun1(n - 1);
    }
*/


}
