package com.mx.sort;

import java.util.Arrays;
import static com.mx.sort.utils.exch;
import static com.mx.sort.utils.greater;

/**
 * @program: StuDemo
 * @description: 冒泡排序  O(N^2)
 *      1. 比较相邻的元素，如果前一个元素比后一个元素大，就交换这两个元素的位置
 *      2. 对每一对相邻元素做同样的工作，从开始第一对元素到结尾的最后一对元素
 *             最终最后位置的元素就是最大值
 * @author: mx
 * @create: 2020-07-29 17:24
 * @Modified By:
 * @Version: 1.0
 **/
public class BubbleSort {
    public static void main(String[] args) {
        Integer[]  a = {9,4,5,2,3,6,8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(Comparable [] arr){
        for(int i = arr.length-1; i>0; i--){
            for(int j=0;j<i;j++){
                if(greater(arr[j],arr[j+1])){
                    exch(arr,j,j+1);
                }
            }

        }
    }
}
