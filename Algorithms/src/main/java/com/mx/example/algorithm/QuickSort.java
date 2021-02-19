package com.mx.example.algorithm;

//快速排序， 也称为分区交换排序，快速排序采用递归调用排序
//快速排序时间复杂度为nlog2n
public class QuickSort {

    public static void quickSort(int arr[],int low, int high){
        int pivot;
        if(low<high){
            pivot = Partition(arr, low, high);
            quickSort(arr, low , pivot-1);
            quickSort(arr, pivot+1, high);
        }
    }
    public  static int Partition(int arr[], int low ,int high){
        int left, right, pivot_item = arr[low];
        left = low;
        right = high;
        while(left<right){
            while(left<right&&arr[right]>=pivot_item){
                --right;
            }
            while(left<right&&arr[left]<=pivot_item){
                ++left;
            }
            if(left<right){
                swap(arr,left,right);
            }
        }
        arr[low]=arr[right];
        arr[right] = pivot_item;
        return  right;
    }
    public static void swap(int arr[], int left, int right){
            if(arr!=null&&arr.length>1){
                int temp = arr[left];
                arr[left]=arr[right];
                arr[right] = temp;
            }
    }
    public static void main(String[] agrs){
        int[] arr = new int[]{11,4,6,13,12,15};
       // bubbleSort(arr);
        quickSort(arr, 0, 5);
        //selectSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    //冒泡排序
    //共进行n-1趟排序，n(n-1)次比较，时间复杂度为T(n)=O(n2)
    public static int[] bubbleSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]= temp;
                }
            }
        }
        return arr;
    }
    //选择排序
    //进行n-1趟排序，n(n-1)/2次比较 时间复杂度为O(n2)
    public static int[] selectSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int k = i;
            //获取最小值的下标
            for(int j=i+1;j<arr.length;j++){
                if(arr[k]>arr[j]){
                    k = j;
                }
            }
            //如果最小值下标不等于标记值，互换
            if(i!=k){
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }

        }
        return arr;
    }

}
