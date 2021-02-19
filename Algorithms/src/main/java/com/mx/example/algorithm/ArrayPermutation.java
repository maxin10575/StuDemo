package com.mx.example.algorithm;

import java.util.LinkedList;

/**
 * TODO:数组全排列
 *
 * @author dangzw
 * @version 1.0, 2018/10/19/11:07
 */
public class ArrayPermutation {
    static int[] arr = {1, 2, 3};
    static int book[] = new int[arr.length];
    static int temp[] = new int[arr.length];
    static LinkedList queue = new LinkedList();

    public static void main(String[] args) {
        //int[] arr = {1, 2, 3};
        //permutations(arr, 0, arr.length-1);
        //dfs(0);
        //bfs(0);
        //System.out.println((1<<30));
        int[] arr2={1,2,3,4};
        int[] book2=new int[arr2.length];
        int[] temp2=new int[arr2.length];
        dfs(0,arr2,book2,temp2);

    }
    public static int hash2(int code){
        return (code>>>16)^(code>>>16);
    }
    public static int hash(int code){
        code ^=(code>>>20)^(code>>>12);
        return code ^ (code >>> 7) ^ (code >>> 4);
    }
    //深度优先算法实现数组的全排列
    public static void dfs(int begin) {
        if (begin == arr.length) {
            printArr(temp);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (book[i] == 0) {
                temp[begin] = arr[i];
                book[i] = 1;
                dfs(begin + 1);
                book[i] = 0;
            }
        }
    }
    public static void dfs(int begin,int[]arr2,int[] book2,int[] temp2){
        if(begin==arr2.length){
            printArr(temp2);
            return;
        }
        for(int i=0;i<arr2.length;i++){
            if(book2[i]==0){
                temp2[begin]=arr2[i];
                book2[i]=1;
                dfs(begin+1,arr2,book2,temp2);
                book2[i]=0;
            }
        }
    }
    //广度优先算法(未实现)
    public static void bfs(int begin){
        for(int i=0;i<arr.length;i++){
            if(book[i]==0){
                book[i]=1;
                queue.offer(arr[i]);
                while(!queue.isEmpty()){
                    temp[i]= (int) queue.poll();
                    if(i==2){
                        printArr(temp);
                    }

                    for(int j=i+1;j<arr.length;j++){
                        if(book[j]==0){
                            book[j]=1;
                            queue.offer(arr[j]);
                        }

                    }
                }
            }
        }

    }
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
    //递归算法实现数组的全排列
    public static void permutations(int[] arr, int begin, int end) {
        if (begin == end) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = begin; i <= end; i++) {
            swap(arr, begin, i);
            permutations(arr, begin + 1, end);
            swap(arr, begin, i);
        }

    }

    private static void swap(int[] arr, int begin, int i) {
        int temp = arr[begin];
        arr[begin] = arr[i];
        arr[i] = temp;
    }

}
