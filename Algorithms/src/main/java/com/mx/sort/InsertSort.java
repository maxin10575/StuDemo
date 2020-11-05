package com.mx.sort;

import java.util.Arrays;

import static com.mx.sort.utils.exch;
import static com.mx.sort.utils.greater;

/**
 * @program: StuDemo
 * @description: 插入排序  O(N^2)
 *      1. 把所有的元素分为两组，已经排序的和未排序的
 *      2. 找到未排序的组中的第一个元素，向已经排序的组中进行插入
 *      3. 倒序遍历已经排序的元素，依次和待插入的元素进行比较，直到找到一个元素小于等于
 *         待插入元素，那么就把待插入元素放到这个位置，其他的元素向后移动一位
 * @author: maxin
 * @create: 2020-07-31 14:07
 * @Modified By:
 * @Version: 1.0
 **/
public class InsertSort {
    public static void main(String[] args) {
        Integer[]  a = {9,4,5,2,3,6,8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }


    public static void sort(Comparable[] a) {
        for(int i=1;i<a.length;i++){
            for(int j=i;j>0;j--){
                //比较索引j处的值和索引j-1处的值，如果a[j] < a[j-1]则交换数据，否则，退出。
                if(greater(a[j-1],a[j])){
                    exch(a,j-1,j);
                }else{
                    break;
                }
            }
        }
    }

}
