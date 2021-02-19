package com.mx.example.algorithm;

import java.util.Arrays;


/**
 * TODO:堆排序
 *
 * @author dangzw
 * @version 1.0, 2018/9/20/9:49
 */
public class HeapSort {
    //时间复杂度 O(logn)
    public static void main(String[] args) {
        int[] arr = {45,12,36,65,11};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        //构造堆
        //从第一个非叶子节点开始调整堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        //调整堆结构+交换堆顶元素与末尾元素
        for(int j = arr.length-1;j>=0;j--){
            swapArr(arr,0,j);
            //重新对堆调整
            adjustHeap(arr,0,j);
        }

    }
    //调整大顶堆，交换堆顶元素和末尾元素
    private static void swapArr(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] =arr[j];
        arr[j] =temp;
    }

    //调整大顶堆
    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//取出当前元素
        for(int k=2*i+1;k<length;k=2*k+1){
            if(k+1<length&&arr[k]<arr[k+1]){
                k++;
            }
            if(arr[k]>temp){
                //如果子节点大于父节点，将子节点赋值给父节点，不再进行交换
                arr[i]= arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp; //将temp值放在最终的位置
    }
}
