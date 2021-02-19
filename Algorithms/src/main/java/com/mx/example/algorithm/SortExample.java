package com.mx.example.algorithm;

/**
 * TODO:
 *冒泡选择排序练习
 * @author dangzw
 * @version 1.0, 2019/2/25/19:05
 */
public class SortExample {
    public static void main(String[] args) {
        int[] arr ={5,2,8,11,3};
        //bubbleSort1(arr);
        selectSort(arr);
        for(int a:arr){
            System.out.print(a+" ");
        }
    }

    private static void bubbleSort1(int[] arr) {
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
    private static void selectSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int k=i;
            for(int j=k+1;j<arr.length;j++){
                if(arr[k]>arr[j]){
                    k=j;
                }
            }
            if(i!=k){
                int temp = arr[i];
                arr[i]=arr[k];
                arr[k]=temp;
            }
        }
    }
}
