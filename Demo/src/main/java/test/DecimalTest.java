package test;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @program: StuDemo
 * @description:    我们在使用BigDecimal时，为了防止精度丢失，推荐使用它的 BigDecimal(String) 构造方法来创建对象。
 *                  禁止使用构造方法BigDecimal（double)
 *                  正例：
 *                        BigDecimal a = new BigDecimal("1.0");
 *                          BigDecimal a = BigDecimal.valueOf(1.0);
 * @author: maxin
 * @create: 2020-05-26 17:31
 * @Modified By:
 * @Version: 1.0
 **/
public class DecimalTest {

    public static void main(String[] args) {
  /*      float a1 = 1.0f - 0.9f;
        float b1 = 0.9f - 0.8f;
        System.out.println(a1);// 0.100000024
        System.out.println(b1);// 0.099999964
        System.out.println(a1 == b1);// false*/
 

   int diagnoseNum = 0;
   int nointactNum = 0;
        BigDecimal  intactNump = BigDecimal.valueOf(diagnoseNum - nointactNum).multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(diagnoseNum), 2, RoundingMode.HALF_UP);
        System.out.println(intactNump);
/*        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);// 0.1
        BigDecimal y = b.subtract(c);// 0.1
        System.out.println(x.equals(y));// true

        System.out.println(a.compareTo(b));// 1

        BigDecimal m = new BigDecimal("1.255433");
        BigDecimal n = m.setScale(3,BigDecimal.ROUND_HALF_DOWN);
        System.out.println(n);// 1.255*/

        //column_name  decimal(P,D);
        //使用decimal mysql中decimal存储类型的使用
        /**
         * D：代表小数点后的位数 P：有效数字数的精度，小数点也算一位 测试例子 数据表的创建：
         * CREATE TABLE `test_decimal` (
         *   `id` int(11) NOT NULL,
         *   `amount` decimal(10,2) NOT NULL
         * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
         */
        //        BigDecimal result =   testDecimal1.getAmount().add(testDecimal2.getAmount());
    }

}
