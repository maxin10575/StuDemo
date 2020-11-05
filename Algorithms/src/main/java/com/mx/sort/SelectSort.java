package com.mx.sort;

import java.util.Arrays;

import static com.mx.sort.utils.exch;
import static com.mx.sort.utils.greater;

/**
 * @program: StuDemo
 * @description: 选择排序   O(N^2)
 * 1. 每一次遍历过程中，都假定第一个索引处的元素是最小值，和其他索引处的值依次进行比较如果当前索引
 * 处的值大于其他某个索引出的值，则假定其他某个索引的值为最小值，最后可以找到最小值所在的索引
 * 2. 交换第一个索引处和最小值所在的索引处的值
 * @author: maxin
 * @create: 2020-07-31 13:26
 * @Modified By:
 * @Version: 1.0
 **/
public class SelectSort {
    public static void main(String[] args) {
        Integer[] a = {9, 4, 5, 2, 3, 6, 8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            //定义一个变量，记录最小元素所在的索引，默认为第一个元素所在位置
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                //比较最小索引minIndex处的值和索引j处的值
                if (greater(a[minIndex], a[j])) {
                    minIndex = j;
                }
            }
            //交换最小元素所在索引minIndex处的值和索引j处的值
            exch(a, i, minIndex);
        }

    }

}
