package ConcurrencyTest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;



public class SellTickets {

    @Test
    public void test() {
//        sellNotThreadSafe();
        sellThreadSafe();
    }

    //unsafeMethod
    private static int totalTickets = 1000;

    private static void sellNotThreadSafe() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable runnableA = new Runnable() {
            int threadATickets = 0;
            @Override
            public void run() {
                while (totalTickets > 0) {
                    totalTickets--;
                    threadATickets++;
                }
                System.out.println("Thread A buys " + threadATickets + " tickets");
            }
        };

        Runnable runnableB = () -> {
            int threadBTickets = 0;
            while (totalTickets >= 0) {
                totalTickets--;
                threadBTickets++;
            }
            System.out.println("Thread B buys " + threadBTickets + " tickets");
        };
        executorService.execute(runnableA);
        executorService.execute(runnableB);
    }

    //safeMethod
    private static AtomicInteger atomicTotalTickets = new AtomicInteger(1000);

    private static void sellThreadSafe() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnableA = () -> {
            int threadATickets = 0;
            while (atomicTotalTickets.get() > 0) {
                atomicTotalTickets.decrementAndGet();
                threadATickets++;
            }
            System.out.println("Thread A buys " + threadATickets + " tickets");
        };

        Runnable runnableB = () -> {
            int threadBTickets = 0;
            while (atomicTotalTickets.get() > 0) {
                atomicTotalTickets.decrementAndGet();
                threadBTickets++;
            }
            System.out.println("Thread B buys " + threadBTickets + " tickets");
        };
        executorService.execute(runnableA);
        executorService.execute(runnableB);
    }


}
