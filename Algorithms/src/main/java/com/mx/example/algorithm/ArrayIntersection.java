package com.mx.example.algorithm;

import java.util.*;
/**
 * TODO:求两个数组的交集
 *
 * @author dangzw
 * @version 1.0, 2019/3/10/17:07
 */
public class ArrayIntersection {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3,3, 5, 8, 9};
        int[] arr2 = new int[]{3,3, 4, 5, 6, 7};
        List<Integer> intersectionList = findArrIntersection(arr1, arr2);
        for (int i : intersectionList) {
            System.out.print(i);
        }
        System.out.println();
        int[] arr3={4,5,6,8,9,1,2,3};
        System.out.println("==="+binarySearch(arr3,3));
    }


    //求两个数组的交集
    public static List<Integer> findArrIntersection(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        boolean flag = true;
        for (int i = 0; i < arr2.length; i++) {
            flag = set.add(arr2[i]);
            if (!flag) {
                list.add(arr2[i]);
            }
        }
        return list;
    }


    public static int binarySearch(int[] arr,int target){
        int low=0;
        int high=arr.length-1;
        while(low<=high){

            int mid=(low+high)/2;

            if(target==arr[mid]){
                return mid;
            }

            if(arr[low]<=arr[mid]){
                if(target<arr[mid]&&target>=arr[low]){
                    high=mid-1;
                }else {
                    low=mid+1;
                }
            }

            if(arr[mid]<=arr[high]){
                if(target>arr[mid]&&target<=arr[high]){
                    low=mid+1;
                }else {
                    high=mid-1;
                }
            }

        }
        return -1;
    }


}