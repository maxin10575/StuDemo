package com.mx.sort;

import org.junit.Test;

/**
 * @Author maxin
 * @Date 2019-09-16 15:01
 * @ClassName QuickSort
 * @Description 快速排序
 * @Version 1.0
 **/

public class QuickSort {

    @Test
    public void test() {
        int []nums = {2,5,7,8,3,4,6,10,5};
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        QuickSort(nums,low,high);

        for(int i=0;i<n;i++){
            System.out.print(nums[i]+",");
        }

    }
        //
        int[] QuickSort(int[] nums, int low, int high){
            if (low < high){
                int temp = partition(nums,low,high);
                QuickSort(nums,low,temp-1);
                QuickSort(nums,high,temp+1);
            }
            return nums;
        }

        //
        int  partition(int[] nums,int low,int high){
            int pivot = nums[low];
            while(low < high){
                while(low<high && nums[high] <= pivot) --high;
                nums[low] = nums[high];
                while(low<high && nums[low] >= pivot) ++low;
                nums[high] = nums[low];
            }
            nums[low] = pivot;
            return low;
        }

    }
