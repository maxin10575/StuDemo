package ConcurrencyTest.threadpool;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*** 
* @Description:  
* @Param:  * @param null 
* @return: 
* @Author: maxin
* @Date: 2020/3/23
* @Modified By:
* @Version: 1.0.0
*/
public class DelayedWorkQueueTest {
    @Test
    public void testScheduledThreadPoolExecutor() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        scheduledExecutorService.execute(()->{});
        scheduledExecutorService.schedule(() -> {
            System.out.println("run schedule : now = " + System.currentTimeMillis());
        }, 3, TimeUnit.SECONDS);


//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            try {
//                Thread.sleep(500L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("run schedule at fixed rate : now = " + System.currentTimeMillis());
//        }, 3, 1, TimeUnit.SECONDS);

//        scheduledExecutorService.scheduleWithFixedDelay(() -> {
//            try {
//                Thread.sleep(500L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("run schedule at fixed rate : now = " + System.currentTimeMillis());
//        }, 3, 1, TimeUnit.SECONDS);


        System.in.read();
    }
}
