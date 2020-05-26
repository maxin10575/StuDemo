package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-04-16 21:27
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {

    @org.junit.Test
    public void test(){

        List<String> list = new ArrayList<String>();

        list.add("刘敏");
        list.add("温柔");
        list.add("可爱");


        for(String a : list) {
            System.out.println(a);
        }
    }
}
