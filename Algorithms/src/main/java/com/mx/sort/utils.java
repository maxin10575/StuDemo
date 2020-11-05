package com.mx.sort;

/**
 * @program: StuDemo
 * @description: 比较大小，交换元素位置
 * @author: maxin
 * @create: 2020-07-31 15:50
 * @Modified By:
 * @Version: 1.0
 **/
public class utils {

    //交换
    public static void exch(Comparable[] arr, int i, int j) {
        Comparable temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //比较大小
    public static boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b)>0;
    }
}
