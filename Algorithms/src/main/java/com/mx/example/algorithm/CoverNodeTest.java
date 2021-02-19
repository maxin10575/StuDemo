package com.mx.example.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CoverNodeTest {
    //百度外卖面试算法题
    //问题，有一个升序的有序数组，用一个长度为L的绳子最多能覆盖几个节点
    public static void main(String[] args) {
        //一个有序的升序数组，不只为正数
        int[] arr = new int[]{-5, 2, 8, 9, 10};
        int len = 12;
        int node = getMaxNodeInArray(arr, len);
        System.out.println("绳子L最大覆盖数组节点数为：" + node);
        AtomicInteger integer = new AtomicInteger();
        String str ="aaab";
        StringBuffer sb = new StringBuffer(str);
        System.out.println(sb.reverse().toString());

    }

    private static int getMaxNodeInArray(int[] arr, int len) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                sum += arr[i];
            } else {
                sum -= arr[i];
            }
        }
        List<Integer> list = new ArrayList<Integer>();
        if (len > sum) {
            return arr.length;
        } else {
            //求最大节点数下的，数组相邻节点和的最小值
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < 0) {
                    arr[i] = -arr[i];
                }
            }
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j];
                for (int k = j + 1; k < arr.length; k++) {
                    if (num <= len && num + arr[k] > len) {
                        list.add(1);
                        continue;
                    }
                    num += arr[k];
                    if (len < num) {
                        continue;
                    } else if (len >= num) {
                        list.add(k - j + 1);
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) >= max) {
                max = list.get(i);
            }
        }
        return max;
    }
}
