package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author maxin
 * @Date 2019-12-13 11:53
 * @ClassName test
 * @Description lambad
 * @Version 1.0
 **/
public class LambdaDemo {

    @org.junit.Test
    public void testArray() {
        Integer [] b ={1,2,3};
        int [] c = {2,3,4};
        String[] arr = {"1", "2", "3"};
        List list = Arrays.asList(b);
        System.out.println("fornt=="+list.toString());
    }

    public static void main(String[] args) {
        new Thread(() -> System.out.println("Lambda!!" + Thread.currentThread())).start();
        new Thread(() -> System.out.println(" Lambda11 !!" + Thread.currentThread())).start();
        LambdaDemo test = new LambdaDemo();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
//
        System.out.println("10 + 5 = " + test.operate(10, 5, addition));
        System.out.println("10 - 5 = " + test.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + test.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + test.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");


        int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);
        //lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
        // num = 5 ;//报错

//在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
        String first = "";
        Comparator<String> comparator = (first1, second) -> Integer.compare(first1.length(), second.length());  //编译会出错
    }

    interface Converter<T1, T2> {
        void convert(int i);
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }


    private int testlambad() {

        return 1;
    }
}