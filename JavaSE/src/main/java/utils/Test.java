package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: DataSync
 * @description:
 * @author:
 * @create: 2021-04-08 14:53
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(SIMPLE_DATE_FORMAT.format(new Date(1621299000000L)));
    }
}
