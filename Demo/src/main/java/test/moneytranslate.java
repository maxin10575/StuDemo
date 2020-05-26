package test;

/**
 * @program: StuDemo
 * @description: 金额转换
 * @author: maxin
 * @create: 2020-04-28 14:46
 * @Modified By:
 * @Version: 1.0
 **/
public class moneytranslate {
    /**
     * @param args add by zxx ,Nov 29, 2008
     */
    private static final char[] data = new char[]{
            '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'
    };
    private static final char[] units = new char[]{
            '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿'
    };

    public static void main(String[] args) {
        System.out.println(
                convert(89123));
    }

    public static String convert(int money) {
        int number = 0;
        StringBuffer sbf = new StringBuffer();
        int unit = 0;
        while (money != 0 && unit < String.valueOf(money).length()-1) {
            sbf.insert(0, units[unit++]);
             number = money % 10;
        }
        sbf.insert(0, data[number]);
        money /= 10;
        return sbf.toString();
    }
}
