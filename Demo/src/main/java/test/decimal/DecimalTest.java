package test.decimal;


/**
 * @program: StuDemo
 * @description:
 * @author: maxin
 * @create: 2020-05-26 17:31
 * @Modified By:
 * @Version: 1.0
 **/
public class DecimalTest {

    public static void main(String[] args) {
        float a = 6.6F;
        float b = 1.3F;
        float c = a+b;
        //c===7.8999996
        System.out.println("c==="+c);

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
