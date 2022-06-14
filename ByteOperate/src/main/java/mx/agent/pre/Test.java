package mx.agent.pre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: maxin
 * @create: 2021-12-08 21:07
 **/
public class Test {
    public static void main(String[] args) {


                List<Integer> list = new ArrayList<>();
                list.add(1);
                System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(",")));

    }

    public void test() {
        int a = 0;
        long c = 1L;
        int b = 0;
    }

    public void localvar2() {
        {
            int a = 0;
            System.out.println(a);
        }
        int b = 0;
    }
}
