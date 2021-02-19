package common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: fangtinghua
 * @date: 2013-07-23
 * @description: 日期工具类，处理日期和字符串之间相互转换
 */
public class DateUtil {
    public static final String Y = "yyyy";
    public static final String dd = "dd";
    public static final String MD = "MM-dd";
    public static final String MDD = "MMdd";
    public static final String M = "MM";
    public static final String MM = "yyyyMM";
    public static final String DD = "yyyyMMdd";
    public static final String H = "HH:00";
    public static final String HM = "HH:mm";
    public static final String HMS = "HH:mm:ss";
    public static final String YM = "yyyy-MM";
    public static final String YMD = "yyyy-MM-dd";
    public static final String YMDH = "yyyy-MM-dd HH";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMSS = "yyyy-MM-dd HH:mm:ss.S";
    public static final String TABLENAME = "yyyyMMdd";
    private static final GregorianCalendar GC = new GregorianCalendar();
    public static final String LONGDATE = "yyyyMMddHHmmss";

    /**
     * 按默认格式解析字符串日期
     *
     * @param date
     * @return Date
     */
    public static Date parse(String date) {
        DateFormat format = new SimpleDateFormat(DEFAULT);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按指定格式解析字符串日期
     *
     * @param date
     * @return Date
     */
    public static Date parse(String date, String format) {
        DateFormat fmt = new SimpleDateFormat(format);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 欧美日期格式转换
     *
     * @param date
     * @return
     */
    public static Date parseUS(String date) {
        DateFormat fmt = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按默认格式解析日期字符串
     *
     * @param date
     * @return Date
     */
    public static String format(Date date) {
        DateFormat format = new SimpleDateFormat(DEFAULT);
        return format.format(date);
    }

    /**
     * 按指定格式解析日期字符串
     *
     * @param date
     * @return Date
     */
    public static String format(Date date, String format) {
        DateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    /**
     * 当前时间+-
     *
     * @param offset
     * @return
     */
    public static String nowDatePlus(int offset, String format) {
        Calendar nowtime = Calendar.getInstance();
        nowtime.add(Calendar.MINUTE, offset);
        return DateUtil.format(nowtime.getTime(), format);
    }

    /**
     * offset为正则往后,为负则往前
     *
     * @param date
     * @param offset
     * @param type
     * @return
     */
    public static Date subtract(Date date, int offset, int type) {
        GC.setTime(date);
        GC.add(type, offset);
        return GC.getTime();
    }

    /**
     * 从开始到结束日期，按照周期返回日期列表
     *
     * @param startTime
     * @param endTime
     * @param cycle
     * @return
     */
    public static List<String> getTimeSpace(Date startTime, Date endTime,
                                            int cycle, int type, String format) {
        List<String> resultList = new ArrayList<String>();
        while (startTime.before(endTime)) {
            resultList.add(format(startTime, format));
            startTime = subtract(startTime, cycle, type);
        }
        return resultList;
    }

    public static List<String> getTimeSpaceMM(Date startTime, Date endTime,
                                              int cycle, int type, String format) {
        List<String> resultList = new ArrayList<String>();
        resultList.add(format(startTime, format));
        if (!startTime.equals(endTime)) {
            do {
                startTime = subtract(startTime, cycle, type);
                resultList.add(format(startTime, format));
            } while (startTime.before(endTime));
        }
        return resultList;
    }

    public static boolean isSameTime(Date date1, Date date2) {
        if (date1.getTime() == date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static Date getDateofPreMonth(Date date) {
        long datelong = date.getTime() / 1000 - 60 * 60 * 24 * 365;
        date.setTime(datelong * 1000);
        return date;
    }


    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(YMD);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


    /**
     * @author xukai
     * 拼接两个日期，取第一个日期的日期部分，取第二个的时分秒部分，合成为一个日期，返回格式为yyyy-MM-dd HH:mm:ss
     **/
    public static Date combine2Date(Date date, Date hour) {
        return parse(format(date, "yyyy-MM-dd") + " " + format(hour, "HH:mm:ss"));
    }

    /**
     * @author xukai
     * 计算两个日期相差的天数（顺序为第二个参数减去第一个参数）
     **/
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {
            return day2 - day1;
        }
    }

    /**
     * @Author xukai
     * 获得一个日期小时部分
     **/
    public static int getHourPart(Date date) {
        return Integer.valueOf(format(date, "HH"));
    }

    /**
     * @Author xukai
     * 计算一个日期的分钟的在一个小时内属于第几个5分钟
     **/
    public static int get5MinCount(Date date) {
        return Integer.valueOf(format(date, "mm")) / 5;
    }

    /**
     * 返回一个日期属于西方星期中的第几天，星期日为1，星期一为2，依次递增，星期六为7
     **/
    public static int getWestWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * @Author xukai
     * @param date 所选日期
     * 获取所选日期的最后一天
     * **/
//    public static Date getLastDayOfMonth(Date date){
//
//    }


    /**
     * @param curDate 当前日期
     * @return
     */
    public static Date dayLater(Date curDate, int step) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(curDate);
        cal.add(GregorianCalendar.DAY_OF_YEAR, step);
        return cal.getTime();
    }

    /**
     * 获取下个月的今天
     *
     * @param curDate 当前日期
     * @return 下个月的今天
     */
    public static Date nextMonth(Date curDate) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(curDate);
        cal.add(GregorianCalendar.MONTH, 1); // 在月份上加1
        return cal.getTime();
    }

    public static Integer returnIndexTime() {
        Date date = new Date();
        String str = format(date, "HH");
        Integer ii1 = Integer.valueOf(str) * 12;
        Integer ii2 = Integer.valueOf(format(date, "mm")) / 5 + 1;
        return ii1 + ii2;
    }


    /**
     * 周期数转换时分秒
     */
    public static String getTime(int cycle, int minute) {
        int x = 60 / minute;

        String time = "";
        int hour = (cycle - 1) / x;
        int min = (cycle - hour * x) * minute;
        if (min == 60) {
            hour = hour + 1;
            min = 0;
        }

        if (hour < 10) {
            time += "0";
        }
        time += hour + ":";
        if (min < 10) {
            time += "0";
        }
        time += min + ":00";

        if (hour == 24) {
            time = "23:59:59";
        }
        return time;
    }

    /**
     * 2个时间段合并成横坐标数组
     *
     * @param fromTime
     * @param toTime
     * @param compFromTime
     * @param compToTime
     * @return
     * @Description TODO
     */
    public static String[] returnAxis(String fromTime, String toTime, String compFromTime, String compToTime) {

        List<String> label1 = getBetweenDates(fromTime, toTime);
        List<String> label2 = getBetweenDates(compFromTime, compToTime);
        if (label1.size() != label2.size()) {
            return null;
        }
        String[] axis = new String[label1.size()];
        for (int i = 0; i < axis.length; i++) {
            axis[i] = label1.get(i).substring(5) + "[" + label2.get(i).substring(5) + "]";
        }
        return axis;
    }

    /**
     * 获取两个日期之间的所有日期（yyyy-MM-dd）
     *
     * @param startTime
     * @param endTime
     * @return
     * @Description TODO
     */
    public static List<String> getBetweenDates(String startTime, String endTime) {
        List<String> dataStrs = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        Date begin = DateUtil.parse(startTime, YMD);
        Date end = DateUtil.parse(endTime, YMD);
        tempStart.setTime(begin);
        while (begin.getTime() <= end.getTime()) {
            dataStrs.add(DateUtil.format(tempStart.getTime(), YMD));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return dataStrs;
    }


    public static void main(String[] args) {

        System.out.println(getTime(5, 5));
    }

    public class format {
        public format(Date oldValue) {
        }
    }
}