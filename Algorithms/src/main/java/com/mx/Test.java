package com.mx;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
        Integer i1=10;
        Integer i2=20;
        swap2(i1,i2);
        System.out.println("i1="+i1+" i2="+i2);

        String str = "|bc";
        String[] str1 = str.split("\\|");
//        String resString = "<?xml aaa/>";
//        String resString2 ="{'aaa':'111'}";
//        String str1 =resString.substring(0,5);
//        String str2 = resString2.substring(0,1);
//        System.out.println(str1);
        System.out.println(str.length());
//        System.out.println(str[0]+"=="+str[1]);
        List list = new ArrayList();
        List list2 = new ArrayList();
        list.remove(1);
        System.arraycopy(list,0,list2,0,list2.size());
 /*       String a = " a b  c   d";
        String s = a.replaceAll("\\s*", "");
        System.out.println(s);*/
//        System.out.println(String.valueOf(reverse1(-123)));
        System.out.println(isPalindrome(88888));
    }


    public static void swap2(Integer i1,Integer i2){
        Integer temp = i1;
        i1 = i2;
        i2 = temp;
        System.out.println(i1+""+i2);
    }
    public static void swap(Integer i1, Integer i2)  {
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            //int temp = i1.intvalue();  //指向同一份内存地址
            Integer temp = new Integer(i1.intValue());
            field.set(i1,i2.intValue());
            field.set(i2,temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isPalindrome(int x) {
        if(x<10 && x >=0){
            return true;
        }
        String str = String.valueOf(x);
        for(int i=0;i<str.length()/2;i++){
            if((str.charAt(i)) != (str.charAt(str.length()-i-1))){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome1(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }


    public static int reverse(int x) {
        String str = String.valueOf(x);
        StringBuilder sb = new StringBuilder(str);
        String result = sb.reverse().toString();
        if(result.contains("-")){
            result = "-" + result.substring(0,result.length()-1);
        }

        if(Long.parseLong(result) >  Integer.MAX_VALUE  || Long.parseLong(result) <  Integer.MIN_VALUE){
            return 0;
        }
        return Integer.parseInt(result);

    }

    public static int reverse1(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }


     /*   List<User> originList = new ArrayList<>();
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
        user4.setAge("5");
        user4.setName("小红");
        user4.setIndex(6.2);

        User user5 = new User();
        user5.setAge("6");
        user5.setName("小1");
        user5.setIndex(2.2);

        User user6 = new User();
        user6.setAge("10");
        user6.setName("小1");
        user6.setIndex(2.2);


        originList.add(user2);
        originList.add(user4);
        originList.add(user5);
        originList.add(user3);
        originList.add(user6);
        originList.add(user1);

*//*        List<String> origin = new ArrayList<>();
        origin.add("2");
        origin.add("3");
        origin.add("5");
        origin.add("6");
        origin.add("8");
        origin.add("9");
        origin.add("18");
        origin.add("1");*//*
       *//* originList.forEach(System.out::println);
        System.out.println("=====");*//*
        originList.sort(((o1, o2) ->
           Integer.valueOf(o1.getAge()).compareTo(Integer.valueOf(o2.getAge())
        )));
        System.out.println("=====");
        originList.forEach(System.out::println);
        System.out.println("******");


        List<String> sdList = new ArrayList<>();
        List<String> sdDetailList = new ArrayList<>();

        for (int i = 0; i < originList.size(); i++) {
            String curSd = originList.get(i).getAge();
            StringBuilder sb = new StringBuilder();
            if (CollectionUtils.isNotEmpty(sdList)) {
                sdList.forEach(sd -> {
                    if (sd.contains(",")) {
                        String[] sdarr = sd.split(",");
                        Arrays.stream(sdarr).forEach(arr -> {
                            sdDetailList.add(arr);
                        });
                    } else {
                        sdDetailList.add(sd);
                    }
                });
            }
            //如果当前时段已经添加到list中，则不进行连续时段查找
            if (CollectionUtils.isNotEmpty(sdDetailList)) {
                if (sdDetailList.contains(curSd)) {
                    continue;
                }
            }

            sb.append(curSd);
            sb = mergeTimeFrame(originList, i, sb);
            sdList.add(sb.toString());
        }
        sdList.forEach(System.out::println);
    }

    public static StringBuilder mergeTimeFrame(List<User> origin, int i, StringBuilder sb) {
        if (i + 1 < origin.size() - 1) {
            if ((Integer.valueOf(origin.get(i).getAge()) + 1) == Integer.valueOf(origin.get(i + 1).getAge())) {
                sb.append(",");
                sb.append(origin.get(i + 1).getAge());
                mergeTimeFrame(origin, i + 1, sb);
            }
        }
        return sb;
    }*/


/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());
        calendar1.add(Calendar.WEEK_OF_YEAR,-1);
        calendar1.set(Calendar.DAY_OF_WEEK, 2);

            String start = sdf.format(calendar1.getTime().getTime());
            calendar1.add(Calendar.DAY_OF_YEAR,6);
            String end = sdf1.format(calendar1.getTime().getTime());
            System.out.println(start);
            System.out.println(end);*/


//           public static final FastDateFormat ISO_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd", Locale.CHINA);
/*           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(new Date());
           calendar.add(Calendar.WEEK_OF_YEAR,-1);
           calendar.set(Calendar.DAY_OF_WEEK, 2);
           String start = sdf.format(calendar.getTime());
           System.out.println(start);*/
/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_YEAR,-1);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        String  start = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR,6);
        String  end = sdf2.format(calendar.getTime());
//        int a = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(start);
        System.out.println(end);
    }*/
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

/* List<User> list = new ArrayList<>();
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
        user5.setAge("2");
        user5.setName("小1");
        user5.setIndex(2.2);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);

           double a = list.stream().mapToDouble(User::getAge).average();
      *//*  List<String> lageList = list.stream().map(User :: getAge).distinct().collect(Collectors.toList());
        lageList.forEach(System.out::println);
        lageList.stream().forEach(age ->{
            double passEfficIndex = list.stream().filter(user -> age.equals(user.getAge())).mapToDouble(User::getAge).average();
        });*//*
        Map<String, List<User>> map =  list.stream().filter(dto -> dto.getIndex()>2 && dto.getIndex()<6).collect(Collectors.groupingBy(User::getAge));

//        List map =  list.stream().filter(dto -> dto.getIndex()>2 && dto.getIndex()<6).collect(Collectors.toList());
        System.out.println(map.size());*/

}


/*
    public static long fun1(long n) {
        if (n == 1) {
            return 1;
        }
        return n * fun1(n - 1);
    }
*/


