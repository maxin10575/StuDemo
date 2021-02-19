package com.mx.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * TODO:BlockQueue构造栈
 *
 * @author dangzw
 * @version 1.0, 2019/1/22/15:18
 */
public class QueueToStack {
    private static BlockingQueue<Integer> queue1;
    private static BlockingQueue<Integer> queue2;

    public QueueToStack(BlockingQueue<Integer> queue1, BlockingQueue<Integer> queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    public static void push(int num) {
        queue1.offer(num);
    }

    public static int pop() throws Exception {
        if(queue1.isEmpty()&&queue2.isEmpty()){
            throw new Exception("the queue is null");
        }
        if(!queue1.isEmpty()){
            if(queue1.size()==1){
                return queue1.poll();
            }
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        }
        if(queue2.size()==1){
            return queue2.poll();
        }
        while (queue2.size()>1){
            queue1.offer(queue2.poll());
        }
        return queue2.poll();

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3,4,5,6,7,8};
        try {
            QueueToStack stack = null;
            stack = new QueueToStack(new ArrayBlockingQueue<>(arr.length), new ArrayBlockingQueue<>(arr.length));
            for (int i = 0; i < arr.length; i++) {
                stack.push(arr[i]);
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.println("第 "+i+"次pop的值为："+stack.pop());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
