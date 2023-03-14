package utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String HH = "HH";

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String HH_MM_SS = "HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }


    public static String formatDate(Date date, String dateFormat) {
        return new DateTime(date).toString(dateFormat);
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 按默认格式解析日期字符串
     *
     * @param date
     * @return Date
     */
    public static String format(Date date) {
        DateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
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
     * 当前时间加减
     * @param offset 单位 天
     * @return
     */
    public static Date dateTimePlus(int offset) {
        Calendar nowtime = Calendar.getInstance();
        nowtime.add(Calendar.DAY_OF_MONTH, offset);
        return nowtime.getTime();
    }

    public static Date parse(String str, String pattern){
        try {
            return DateUtils.parseDate(str, pattern);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean timeIsToday(Long millis){
        long nd = 1000 * 24 * 60 * 60;
        Date date = DateUtils.parse(DateUtils.getDate(),YYYY_MM_DD);
        long num = millis - date.getTime();
        if (num>=0 && num<nd) {
            return true;
        }
        return false;
    }


/*    public static void main(String[] args) {
        long nd = 1000 * 24 * 60 * 60;
        System.out.println(DateUtils.timeIsToday(System.currentTimeMillis()+nd));
        System.out.println(DateUtils.timeIsToday(System.currentTimeMillis()));
        System.out.println(DateUtils.timeIsToday(System.currentTimeMillis()-nd));
    }*/

}
