package com.mx.example.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * TODO:循环有序数组查找
 *
 * @author mx
 * @version 1.0, 2019/2/21/12:47
 */
public class BinarySerach {
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 1, 2, 3, 4};
        int x = 3;
        int[] arr2 = {1, 2, 2, 3, 3, 3, 5, 6, 6, 7};
        int index = binarySearchLeftIndex(arr2, 2);
        int index2 = binarySearchRightIndex(arr2, 2, 0, 2);
        System.out.println(index);
        Set<Object> set =new TreeSet<>();


        Map map = new LinkedHashMap();
        for (int i:arr2) {
            System.out.print(i+" ");
        }
    }

    public static int binarySearch3(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (x < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid;
            }
            if (arr[high] == x) {
                return high;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (x == arr[mid]) {
                return mid;
            }
            if (arr[low] <= arr[mid]) {
                if (x < arr[mid] && x >= arr[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            if (arr[mid] <= arr[high]) {
                if (x > arr[mid] && x <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
    //获取重复有序数组最左下标
    public static int binarySearchLeftIndex(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                if (mid > low && arr[mid] == arr[mid - 1]) {
                    high = mid - 1;
                } else {
                    return mid;
                }
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //二分查找查找目标值，在区间[start,end]之间查找target,有重复元素返回最后一个元素下标，其他返回-1
    public static int binarySearchRightIndex(int[] arr, int target, int start, int end) {
        int low = start;
        int high = end;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                if (mid < high && arr[mid] == arr[mid + 1]) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        return -1;
    }

    //二分查找经典实现
    public static int binarySearchClassical(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (x == arr[mid]) {
                return mid;
            }
            if (x > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
