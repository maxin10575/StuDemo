package com.mx;

import cn.hutool.core.date.DateUtil;
import com.mx.uf.Base64Utils;
import com.mx.uf.Md5Utils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @program: StuDemo
 * @description: 递归测试
 * @author:
 * @create: 2020-07-29 15:28
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {


    @org.junit.Test
    public void testToString() {
        List<User> userList = new ArrayList<>();
        System.out.println(userList);
    }

    @org.junit.Test
    public void longToTime() {
            try {
                Long time = 1641828603L;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(time*1000);
                System.out.println( sdf.parse(format));
            } catch (ParseException e) {
                e.printStackTrace();
            }

    }

    @org.junit.Test
    public  void testBase64() {
        String a = "maxin1111";
        String a1 = Base64Utils.encodeBase64ToStr(a);
        System.out.println(a1);
        System.out.println(Base64Utils.decodeBase64ToStr(a1));

        String b = "fasdddddddddddddddddddddddd3wefshgrshfghsfghfghfghfghfdghdfghfdh";
        String b1 = Base64Utils.encodeBase64ToStr(b);
        System.out.println(b1);
        System.out.println(Base64Utils.decodeBase64ToStr(b1));

    }

    @org.junit.Test
    public void testW(){
        int a = 10;
        int b = 0;
        while (a>1){
            b++;
            if(a<8){
                return;
            }
            System.out.println("第"+b+"次");
            a--;
        }
        System.out.println("结束");
    }

    @org.junit.Test
    public void testMd5() {
//        String b ="0d59b3b41d1a3b1105 ee6539214f4bde8c9d2dbf19a03e69ff";
        String a = "0d59b3b41d1a3b1105" + "%22update_time%22%3A+%222018-01-01+00%3A00%3A00%22%2C%0A++++%22tid%22%3A+%22E20200728132836008700133%22"+"ee6539214f4bde8c9d2dbf19a03e69ff";
        System.out.println(Md5Utils.hash(a));
        System.out.println(URLEncoder.encode("\"update_time\": \"2018-01-01 00:00:00,\"tid\": \"E20200728132836008700133\""));
    }

    @org.junit.Test
    public void testDate(){
        System.out.println(DateUtil.beginOfYear(new Date()));
        System.out.println(DateUtil.endOfYear(new Date()));
    }

    /*16进制求和  适合任何16进制数*/
//    @org.junit.Test
//    public void makeChecksum2(String num, int count) {
//        //拆分每个16进制字符串
//        String[] split = num.trim().split("");
//  /*  for (int i = split.length - 1; i > -1; i--) {
//        int size = hexToInteger(split[i]) + count;
//        int valuen = size % 16;
//        split[i] = encodeHEX(valuen);
//        count = size / 16;
//    }*/
//        int i = split.length - 1;
//        StringBuilder builder = new StringBuilder();
//        while(count != 0){
//            if (i >= 0){
//                int size = hexToInteger(split[i]) + count;
//                int valuen = size % 16;
//                split[i] = encodeHEX(valuen);
//                count = size / 16;
//            }else {
//                int valuen = count % 16;
//                if (valuen > 0){
//                    builder.append(encodeHEX(valuen));
//                }
//                count = count / 16;
//            }
//            i--;
//        }
//        StringBuilder str = new StringBuilder();
//        if(builder.length() > 0){
//            String[] bsplit = builder.toString().split("");
//            for (int s = bsplit.length - 1; s > -1; s--) {
//                str.append(bsplit[s]);
//            }
//        }
//        for (int n = 0; n < split.length; n++) {
//            str.append(split[n]);
//        }
////        return str.toString();
//    }

    //10进制转成16进制数
    public static String encodeHEX(Integer numb) {
        String hex = Integer.toHexString(numb);
        return hex;
    }

    //16进制数转成10进制
    public static Integer hexToInteger(String hex) {
        Integer num = Integer.parseInt(hex, 16);
        return num;
    }

    @org.junit.Test
    public void testG() {
//        System.out.println(String.format("%02X", 20));
        System.out.println(Integer.parseInt("00", 16));
    }


    @org.junit.Test
    public void testF(){
        String cmdStr = "5E010403";
            Long cmdLong = 0L;
            int cmdStrLen = cmdStr.length() / 2;
            for (int i = 0; i < cmdStrLen ; i++) {
                String sCmd = cmdStr.substring(2*i,2*i+2);
                cmdLong += Long.parseLong(sCmd, 16);
            }
             System.out.println(Long.toHexString(cmdLong));
    }

    @org.junit.Test
    public void testE(){
        Boolean flag = true;
        String[] StringArr = new String[1];
        try {
            String a = StringArr[2];
        } catch (Exception e) {
            flag = false;
            throw new RuntimeException(e);
//            e.printStackTrace();
        }
        System.out.println(flag);
    }


    @org.junit.Test
    public void getDate() throws ParseException {
    //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d2=sdf.parse("2021-11-12 17:30:30");//将String to Date类型
//        System.out.println(d2.getTime());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        System.out.println("abcd".substring(0,"abcd".length()-1));

//        System.out.println(8L/3L);
//        System.out.println(new BigDecimal(2.4234).setScale(0,RoundingMode.HALF_UP).longValue());

//        System.out.println(testEnmu.getDescByCode(1));
    }

    @org.junit.Test
    public void testGroup(){
        User user1 = User.builder().age(11).name("mark").index(0.1).brithday("2022-06-20 08:20:12").build();
        User user2 = User.builder().age(22).name("tony").index(0.4).brithday("2022-06-19 12:20:12").build();
        User user3 = User.builder().age(3).name("tom").index(0.3).brithday("2022-06-20 07:20:12").build();
        List<User> userList = new ArrayList();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        List<User> userNewList = new ArrayList<>();
        userNewList.addAll(userList);
        System.out.println(userNewList);
        User userLast = userNewList.stream().min(Comparator.comparing(User::getBrithday)).get();
        System.out.println(userLast.getBrithday());



//        double currentLevel = userList.stream().filter(category -> category.getAge().equals(22)).map(User::getIndex).findAny().get();
//System.out.println(currentLevel);


/*        List<User> newuserList = new ArrayList<User>();

        userList.parallelStream().collect(Collectors.groupingBy(o -> o.getName(), Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a, b) -> new User(a.getName(),a.getAge() + b.getAge(),a.getIndex()+b.getIndex())).ifPresent(newuserList:: add);
                });
        System.out.println("处理过的List:--");
        newuserList.forEach(item->{
            System.out.println(item.toString());
        });*/

        //        Map<String,User> aaa = list.stream().collect(Collectors.groupingBy(User::getName));
        //a
//        Map<Long,List<Long>> exhibitionPitemMap = list.stream().collect(Collectors.groupingBy(TestDTO1::getLevle1CategoryId, Collectors.mapping(TestDTO1::getPitemId, Collectors.toList())));
//b
//        Map<Long, List<TestDTO2>> categoryPitemMap = list.stream().collect(Collectors.groupingBy(TestDTO2::getLevle1CategoryId));
    }

    @org.junit.Test
    public void testEnum(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(simpleDateFormat.format(1638251244828L));
        System.out.println(simpleDateFormat.format(1637999292459L));
    }


    @org.junit.Test
    public void testaList(){

        System.out.println(Arrays.asList(1,"haha",true));

    }

    @org.junit.Test
    public void test111(){
//        System.out.println(com.mx.AttributeTypeEnum.getDesc(1));
//        System.out.println(com.mx.AttributeTypeEnum.getAllPhoneCreditType());
        String a = "c=d";
        String arr[] = a.split("=");
        System.out.println(arr[arr.length-1]);
//        System.out.println(com.mx.VirtualTypeEnum.getDesc(1));
    }

    @org.junit.Test
    public void phone() {
        String tel = "13312341234";
        String ct = "^1((33|49|53|73|77|80|81|89|99)[0-9])\\d{7}$";
        String cu = "^1(30|31|32|45|55|56|66|71|75|76|85|86)\\d{8}$";
//          String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";
        if (StringUtils.isNotEmpty(tel)) {
            System.out.println(Pattern.matches(ct, tel));
        }
    }

    @org.junit.Test
    public void findMedianSortedArrays() {
        int[] nums1 = {1,5};
        int[] nums2 = {3, 4};
        int len = nums1.length + nums2.length;
        int[] a = new int[len];
        System.arraycopy(nums1,0,a,0,nums1.length);
        System.arraycopy(nums2,0,a,nums1.length,nums2.length);
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "，");
        }
        if (len % 2 != 0) {
            System.out.println("===="+a[(int) (len / 2 + 0.5)]);
        } else {
            System.out.println("===="+(double)(a[len / 2 -1 ] + a[len / 2]) / 2);
        }
    }


    public static void main(String[] args) throws ParseException {
        Integer i1 = 10;
        Integer i2 = 20;
        swap2(i1, i2);
        System.out.println("i1=" + i1 + " i2=" + i2);

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
        System.arraycopy(list, 0, list2, 0, list2.size());
 /*       String a = " a b  c   d";
        String s = a.replaceAll("\\s*", "");
        System.out.println(s);*/
//        System.out.println(String.valueOf(reverse1(-123)));
        System.out.println(isPalindrome(88888));
    }


    public static void swap2(Integer i1, Integer i2) {
        Integer temp = i1;
        i1 = i2;
        i2 = temp;
        System.out.println(i1 + "" + i2);
    }

    public static void swap(Integer i1, Integer i2) {
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            //int temp = i1.intvalue();  //指向同一份内存地址
            Integer temp = new Integer(i1.intValue());
            field.set(i1, i2.intValue());
            field.set(i2, temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isPalindrome(int x) {
        if (x < 10 && x >= 0) {
            return true;
        }
        String str = String.valueOf(x);
        for (int i = 0; i < str.length() / 2; i++) {
            if ((str.charAt(i)) != (str.charAt(str.length() - i - 1))) {
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
        if (result.contains("-")) {
            result = "-" + result.substring(0, result.length() - 1);
        }

        if (Long.parseLong(result) > Integer.MAX_VALUE || Long.parseLong(result) < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.parseInt(result);

    }

    public static int reverse1(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
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
        user5.setAge("2");
        user5.setName("小1");
        user5.setIndex(2.2);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);*/

//    List<String> lageList = list.stream().filter(dto -> dto.getIndex()>2 && dto.getIndex()<6).collect(Collectors.toList());
//           double a = list.stream().mapToDouble(User::getAge).average();
      /*  List<String> lageList = list.stream().map(User :: getAge).distinct().collect(Collectors.toList());
//        lageList.forEach(System.out::println);
//        list.stream().forEach(age ->{
//            double passEfficIndex = list.stream().filter(user -> age.equals(user.getAge())).mapToDouble(User::getAge).average();
//        });*//*
        Map<String, List<User>> map =  list.stream().filter(dto -> dto.getIndex()>2 && dto.getIndex()<6).collect(Collectors.groupingBy(User::getAge));

//        List map =  list.stream().filter(dto -> dto.getIndex()>2 && dto.getIndex()<6).collect(Collectors.toList());
//        System.out.println(map.size());

}


/*
    public static long fun1(long n) {
        if (n == 1) {
            return 1;
        }
        return n * fun1(n - 1);
    }
*/
}

