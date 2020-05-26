package ConcurrencyTest.threadpool;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/***
* @Description: 线程池常用队列
* @Author: maxin
* @Date: 2020/3/23
* @Modified By:
* @Version: 1.0.0
*/
public class LinkedBlockingQueueTest {

    @Test
    public void testEmptyPoll() {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(1);
        try {
            linkedBlockingQueue.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
