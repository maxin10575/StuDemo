package com.mx.example.algorithm;

import java.util.PriorityQueue;

/**
 * TODO:
 *
 * @author mx
 * @version 1.0, 2019/3/12/14:04
 */
public class KthMaxNum {
    public static void main(String[] args) {
        int[] arr={1,2,4,5,7};
        int k=3;
        int kthMaxNum = getKthMaxNum(arr,k);
        System.out.println(kthMaxNum);
    }

    private static int getKthMaxNum(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int n:arr){
            if(queue.size()<k){
                queue.offer(n);
            }else if(queue.peek()<n){
                queue.poll();
                queue.offer(n);
            }
        }
        return queue.peek();
    }
}
