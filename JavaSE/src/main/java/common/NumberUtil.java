package common;

import java.text.DecimalFormat;

/**
 * @author: mx
 * @date: 2013-07-23
 * @description: 日期工具类，处理日期和字符串之间相互转换
 */
public class NumberUtil {
    public static final String N = "#,###";

    public static String formatTosepara(int value) {
        DecimalFormat df = new DecimalFormat(N);
        return df.format(value);
    }

}