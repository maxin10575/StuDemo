package com.mx.example.highConcurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * TODO:消费者生产者
 *
 * @author mx
 * @version 1.0, 2019/1/22/11:47
 */
public class CousumerProducer {
    public static final String EXIT_MSG = "Good Bye!";

    public static void main(String[] args) {
        //使用较小的队列，以更好地在输出中展示其影响
        //ArrayBlockingQueue是典型的有界队列，内部以final的数组保存数据的大小，数组的大小就决定了队列的边界，
        //所以创建ArrayBlockingQueue时，指定容量
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {
        private BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    Thread.sleep(5L);
                    String msg = "Message" + i;
                    System.out.println("Producer new item: " + msg);
                    queue.put(msg);
                }
                queue.put(EXIT_MSG);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                String msg;
                while (!EXIT_MSG.equalsIgnoreCase(msg = queue.take())) {
                    System.out.println("Consumer item: " + msg);
                    Thread.sleep(10L);
                }
                System.out.println("Got exit message, bye!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
