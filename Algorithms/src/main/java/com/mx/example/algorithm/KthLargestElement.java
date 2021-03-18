package com.mx.example.algorithm;

import java.util.PriorityQueue;

/**
 * TODO:流式数据获取第K大元素
 *
 * @author mx
 * @version 1.0, 2019/1/11/18:14
 */
public class KthLargestElement {
    //优先队列
    final PriorityQueue<Integer> q;
    static int K;


    public KthLargestElement(int[] arr, int k) {
        this.K = k;
        q = new PriorityQueue<>(k);
        for(int n:arr){
            add(n);
        }
        System.out.println(q.peek());
    }

    private int add(int n) {
        if(q.size()<K){
            q.offer(n);
        }else if(q.peek()<n){
            q.poll();
            q.offer(n);
        }
        return q.peek();
    }

    public static void main(String[] args) {
        int arr[] = {2,3,5,1,4,9};
        K = 3;
        new KthLargestElement(arr, K);
    }
}
