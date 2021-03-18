package common;


import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description：日期工具类
 * @author：mx
 * @date：2020-07-09 10:23
 */
public class TimeUtil {

    /**
     * 声明一个静态map对象，用以存取小时点
     */
    public static Map<String, Object> mapList = new HashMap<>();
    /**
     * 执行方法耗费时间单位秒
     */
    public static final String unitTime = "s";


    /**
     * @description:获取当前日期，日期格式yyyy-MM-dd
     * @author：mx
     * @date：2020-07-09 10:25
     * @param
     * @exception/throws 无
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = simpleDateFormat.format(new Date());
        return nowDate;
    }

    /**
     * @description:获取当前时间，日期格式yyyy-MM-dd HH:mm:ss
     * @author：mx
     * @date：2020-07-09 10:38
     * @param
     * @exception/throws 无
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(new Date());
        LocalTime.now();
        return nowTime;
    }

    /**
     * @description:获取当天凌晨时间，日期格式yyyy-MM-dd HH:mm:ss
     * @author：mx
     * @date：2020-07-14 16:18
     * @param
     * @exception/throws 无
     * @return
     */
    public static Long getTodayBeforeDawn(){
        Date start = getCalendar().getTime();
        return start.getTime();
    }

    /**
     * @description:获取明天凌晨时间，日期格式yyyy-MM-dd HH:mm:ss
     * @author：mx
     * @date：2020-07-14 16:21
     * @param
     * @exception/throws 无
     * @return
     */
    public static Long getTomorrowBeforeDawn(){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DATE, +1); // 明天的就是+1，昨天是-1
        return  calendar.getTimeInMillis();
    }

    /**
     * @description:获取昨天凌晨时间，日期格式yyyy-MM-dd HH:mm:ss
     * @author：mx
     * @date：2020-07-14 16:39
     * @param
     * @exception/throws 无
     * @return
     */
    public static Long getYesterDayBeforeDawn(){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DATE, -1); // 明天的就是+1，昨天是-1
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return  calendar.getTimeInMillis();
    }

    /**
     * @description:获取上周当天凌晨时间，日期格式yyyy-MM-dd HH:mm:ss
     * @author：mx
     * @date：2020-07-14 16:39
     * @param
     * @exception/throws 无
     * @return
     */
    public static Long getLastWeekTodayBeforeDawn(){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DATE, -7);
        return  calendar.getTimeInMillis();
    }

    /**
     * @description:获取上周当天之后的一天凌晨时间，日期格式yyyy-MM-dd HH:mm:ss
     * @author：mx
     * @date：2020-07-14 16:39
     * @param
     * @exception/throws 无
     * @return
     */
    public static Long getLastWeekTodayAfterBeforeDawn(){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DATE, -6);
        return  calendar.getTimeInMillis();
    }

    /**
     * @description: 获取一个Calendar对象
     * @author：mx
     * @date：2020-07-14 16:21
     * @param
     * @exception/throws 无
     * @return
     */
    public static Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * @description: 获取一个Calendar对象,根据类型来生成不同的对象
     * @author：mx
     * @date：2020-07-16 15:19
     * @param type 时间点
     * @exception/throws 无
     * @return
     */
    public static Calendar getCalendar(String type){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (String key : mapList.keySet()) {
            if (key.equals(type)) {
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mapList.get(key).toString()));
                break;
            }
        }
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * @description: 获取今日其他时间点的时间戳
     * @author：mx
     * @date：2020-07-16 15:31
     * @param
     * @exception/throws 无
     * @return
     */
    public static Long getTodayOtherTime(String type){
        Calendar calendar = getCalendar(type);
        Date start = calendar.getTime();
        return start.getTime();
    }

    /**
     * @description: 获取一个Calendar对象,根据传入不同的整型值
     * @author：mx
     * @date：2020-07-17 05:24
     * @param type 时间点
     * @exception/throws 无
     * @return
     */
    public static Calendar getCalendar(Integer type){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, type);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * @description: 获取一个Calendar对象,根据传入不同的高峰时间点
     * @author：mx
     * @date：2020-07-17 05:24
     * @param peakTime 高峰时间点
     * @exception/throws 无
     * @return
     */
    public static Calendar getCalendar(String peakTime, Integer dateNum){
        Calendar calendar = Calendar.getInstance();
        String[] peakTimes = peakTime.split(":");
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(peakTimes[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(peakTimes[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, dateNum);
        return calendar;
    }

    /**
     * @description: 根据传入的num参数获取今日时间戳,num值范围小于等于23
     * @author：mx
     * @date：2020-07-17 05:24
     * @param num 时间点
     * @exception/throws 无
     * @return
     */
    public static Long getTodayByNum(Integer num){
        Calendar calendar = getCalendar(num);
        Date start = calendar.getTime();
        return start.getTime();
    }

    /**
     * @description: 根据传入的时间高峰区间和num参数，算时间戳
     * @author：mx
     * @date：2020-07-19 22:55
     * @param peakTime 时间点 dateNum日期天数
     * @exception/throws 无
     * @return
     */
    public static Long getTodayByPeakTimeAndNum(String peakTime,Integer dateNum){
        Integer time = Integer.parseInt(peakTime);
        /**
         * 根据peakTime获取Calendar对象
         */
        Calendar calendar = getCalendar(time);
        calendar.add(Calendar.DATE, dateNum);
        return calendar.getTimeInMillis();
    }

    /**
     * @description: 根据传入的时间高峰区间和num参数，算时间戳
     * @author：mx
     * @date：2020-07-30 06:57
     * @param peakTime 时间点，格式HH:mm dateNum日期天数
     * @exception/throws 无
     * @return
     */
    public static Long getPeakTime(String peakTime,Integer dateNum){
        Calendar calendar = getCalendar(peakTime, dateNum);
        return calendar.getTimeInMillis();
    }

}
