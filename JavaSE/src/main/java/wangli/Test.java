package wangli;

import utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: maxin
 * @create: 2022-08-16 14:03
 **/
public class Test {

    /**
     * 禁止 布防/撤防 开始时间
     */
    private String sleepStartDate = "22:00:00";

    /**
     * 禁止 布防/撤防 结束时间
     */
    private String sleepEndDate = "07:00:00";

    @org.junit.Test
    public void testList(){
      String a = "6c644395887bbd1693vqch6.4";
      System.out.println(a.split("6.4")[0]);
      System.out.println(a.substring(0,a.length()-3));

    }

    @org.junit.Test
    public void testDate(){
        Calendar calendar = Calendar.getInstance();
        // 设置时间为昨天18点
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date yesterday18 = calendar.getTime();
        // 设置时间为今天18点
        calendar.add(Calendar.DATE, 1);
        Date today18 = calendar.getTime();
        // 打印结果
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("昨天18点的日期：" + sdf.format(yesterday18));
        System.out.println("今天18点的日期：" + sdf.format(today18));
    }

    @org.junit.Test
    public void test11(){
        int a = 10;
        int b = 11;
        System.out.println(a/b);
    }

    @org.junit.Test
    public void test() throws ParseException {
//        System.out.println(1 ^ 1) ;
//        System.out.println(0 ^ 1) ;
        Long  currentTime = System.currentTimeMillis();
        String hour = DateUtils.formatDate(new Date(),DateUtils.HH);
        System.out.println(hour);

        Long startTime  = DateUtils.parseDate(DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD)+" "+sleepStartDate,DateUtils.YYYY_MM_DD_HH_MM_SS).getTime();
        Long endTime = DateUtils.parseDate(DateUtils.formatDate(new Date(),DateUtils.YYYY_MM_DD)+" "+sleepEndDate,DateUtils.YYYY_MM_DD_HH_MM_SS).getTime();
        System.out.println(currentTime);
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @org.junit.Test
    public void testT(){
        int a = 0;
        String num = "522, 543, 551, 568, 551, 557, 570, 553, 568, 559, 568, 558, 561, 344, 563, 368, 549, 556, 549, 453, 548, 542, 569, 571, 165, 173, 224, 80, 508, 569, 550, 562, 554, 557, 551, 572, 559, 568, 568, 552, 559, 567, 567, 571, 543, 559, 187";
        String[] arr = num.split(", ");
        for (String s : arr) {
          a += Integer.parseInt(s);
        }
        System.out.println(a);
    }

    @org.junit.Test
    public void testM(){
        System.out.println(String.format("%04X",  Math.abs(-300)));
        System.out.println(String.format("%04X", 300));
    }

}
