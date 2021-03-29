package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: StuDemo
 * @description:
 * @author: mx
 * @create: 2021-03-22 22:49
 * @Modified By:
 * @Version: 1.0
 **/
public class YaoHaoTest {
    public static void main(String[] args) {
        //房屋套数
        int fwCount = 300;
        //总报名人数
        int amount = 3400;
        //我的号码
        int mycount = 260;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= fwCount; i++) {
            list.add(new Random().nextInt(amount) + 1);
        }
        if (list.contains(mycount)) {
            System.out.println("恭喜中签！");
        }else {
            System.out.println("很遗憾！");
        }
    }
}
