package com.mx.sort;

import java.util.Arrays;

import static com.mx.sort.utils.exch;
import static com.mx.sort.utils.greater;

/**
 * @program: StuDemo
 * @description: 希尔排序
 * @author: mx
 * @create: 2020-09-23 14:15
 * @Modified By:
 * @Version: 1.0
 **/
public class ShellSort {
    public static void main(String[] args) {
        Integer[] a = {9, 4, 5, 2, 3, 6, 8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(Comparable[] a) {
        //1.根据数组a长度，确定增长量h的初始值
        int h=1;
        while(h <= a.length/2){
            h=2*h+1;
        }
        //找到待插入的元素
        while (h>=1){
            for(int i=h;i<a.length;i++){
                //把待插入的元素插入到有序数列中
                for(int j=i;j>=h;j-=h){
                    if(greater(a[j-h],a[j])){
                        exch(a,j-h,j);
                    }else {
                        break;
                    }
                }
            }
            h = h/2;
        }
    }

}
