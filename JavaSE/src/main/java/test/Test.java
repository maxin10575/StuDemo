package test;

import java.math.BigDecimal;
import java.util.Objects;

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
        BigDecimal bigDecimal1 = new BigDecimal(0);
//        System.out.println(bigDecimal1.divide(new BigDecimal(100),5,BigDecimal.ROUND_HALF_UP));
        System.out.println(Objects.isNull(bigDecimal1));
/*        List<User> userList = Lists.newArrayList();
        User user = new User();
        user.setId("1");
        user.setName("11");
        userList.add(user);

        User user1 = new User();
        user1.setId("2");
        user1.setName(null);
        userList.add(user1);

        List<User> userList1 = userList.stream().filter(o-> Objects.nonNull(o.getName())).collect(Collectors.toList());

        userList1.forEach(o->{
            System.out.println(o);
        });*/

  /*      List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        List<Integer> intList2 = new ArrayList<>();
       intList2.addAll(intList);
        intList.add(3);
        intList.add(4);
        intList.add(5);*/
    }
/*        String a = null;
        String b = "11";
        System.out.println(Objects.equals(a,b));*/
/*        List<Integer> intList1 = new ArrayList<>();



        List<Integer> intList2 = null;
//        intList2.add(7);
//        intList2.add(8);
//        intList2.add(9);
//        intList2.add(10);

        if(CollectionUtils.isNotEmpty(intList)){
            intList1 = intList;
        }
        if(CollectionUtils.isNotEmpty(intList2)){
            intList1.addAll(intList2);
        }
        for (Integer integer : intList1) {
            System.out.println(integer);
        }

        }*/

/*        User user = new User();
        user.setId("1");
        user.setName("11");
        Preconditions.checkNotNull(user,"有空值");*/


 /*       String[] arr = new String[]{"11"};
        System.out.println(arr[1]);*/

/*        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        List<Integer> intList2 = new ArrayList<>();
        intList2.add(5);
        intList2.add(7);
        intList2.add(2);
        intList2.add(4);
        intList2.add(3);
        intList2.add(1);
        List<Integer> intersection = intList.stream().filter(item -> !intList2.contains(item)).collect(toList());
        intersection.stream().forEach(System.out::println);*/

        /*
        intList.forEach(a->{
            for (Integer b : intList2) {
                if (a == b) {
                    System.out.println("相等");
                    continue;
                }
            }
            System.out.println("a==="+a);
        });*/



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
