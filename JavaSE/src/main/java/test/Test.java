package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2021-01-28 16:44
 * @Modified By:
 * @Version: 1.0
 **/
public class Test {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        List<Integer> intList2 = new ArrayList<>();
        intList2.add(5);
        intList2.add(2);
        intList2.add(4);
        intList2.add(3);
        intList2.add(1);
        intList.forEach(a->{
            for (Integer b : intList2) {
                if (a == b) {
                    System.out.println("相等");
                    continue;
                }
            }
            System.out.println("a==="+a);
        });



       /*     byte[] allocation1, allocation2;
            allocation1 = new byte[30900*1024];
            //allocation2 = new byte[900*1024];*/

     /* int a = 2;
      while (true){
          if(a == 2){
              break;
          }
          System.out.println("111");
      }*/
    }
}
