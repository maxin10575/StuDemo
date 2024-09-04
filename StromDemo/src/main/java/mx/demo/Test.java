package mx.demo;

import java.text.SimpleDateFormat;

/**
 * @author zhuhuipei
 * @Description:
 * @date 2020-08-21
 * @time 22:04
 */
public class Test {

    public static void main(String[] args) {
        SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true) {
            System.out.println(SIMPLE_DATE_FORMAT.format(System.currentTimeMillis()));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
