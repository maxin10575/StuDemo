package ConcurrencyTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: studying
 * @description: synchronized测试=
 *
 * 1.同步概念：阻塞式调用，调用方必须等待响应方执行完毕才返回；
 * 2.异步：非阻塞式调用，立即返回，调用方无需等待响应方返回实际结果，响应方会通过状态、通知或回调来告知调用方；
 *    使用场景：耗时任务或电商下单链路的非核心链路调用；
 * 3.优缺点对比:
 *      同步优点：可拿实时结果进行处理，上下文信息始终在一个代码块，处理更直观；对错误和异常处理可以实时；
 *      异步优点：不影响主流程的执行，降低响应时间，提高应用性能；及时释放系统资源，如线程占用，让系统做更有价值的事；
 *      同步缺点：耗时的接口响应会影响整个流程的性能；
 *      异步缺点：为保障数据最终一致性，需对账系统去做监控和保障；需更多异步任务去补偿系统间数据一致性；
 * 4.阻塞：调用结果返回之前，当前线程被挂起。调用线程只有在得到结果之后才返回。ServerSocket；
 * 5.非阻塞：非阻塞在不能立刻得到结果前，该调用不会阻塞当前线程，而立即返回；Socket;
 * 6.对比：
 *      同步异步：关注点是得到结果的方式；同步是实时返回结果，异步是通过共享变量、通知或回调来得到结果；
 *         阻塞非阻塞：关注点在程序等待调用结果返回时的状态；
 *
 * 线程安全：
 *  1.定义：一个类被多个线程以任意方式同时调用，且不需要外部额外同步和协同的情况下，仍保持内部数据正确且表现正确的行为；
 *  2.等级：
 *  不可变类：一定线程安全，final修饰的不可变类String等，枚举类；
 *  线程安全类：该类任意操作都不会使该对象的数据处于不一致或数据污染的情况，LinkedBlockingQueue：
 *  有条件线程安全类：对于单独访问类的方法是线程安全，但对于某些复合操作，需要外部类来同步；如包含.contains/.add的方法；添加synchronized关键字同步；
 *  线程兼容类：需要正确使用同步；用锁或synchronized包含每一个方法调用；
 *  线程对立类；不管是否调用了外部同步都不能在并发使用时保证其安全；
 *
 *
 *  一、 ScheduledExecutorService 使用场景
 * 1. 定时执行异步任务
 * 2. 周期性异步任务
 *     周期性异步执行的 run() 巧妙在于，通过 Period 参数来控制周期性。也就是周期性任务每次执行的时候(isPeriod)，就会同时计算该周期性任务下次执行的时间，并且放入队列中。
 * 二、 ScheduledExecutorService 注意点
 * 1. 线程池任务和异常监控和告警，及时了解任务运行状况。
 * 2. 周期性执行的任务，需要注意任务的执行时间，避免业务影响
 * 3. 注意异常处理，抛出异常后，任务将终止周期性执行
 * * 还有哪些框架提供定时执行任务功能？
 *  *      1. Spring 框架提供的定时执行器
 *  *      @Scheduled(cron = "0 0 0 * * ?") //每天晚上12点执行
 *  *      @Scheduled(cron = "0 0 0 * * ?")  //每天晚上12点执行
 *  *     @RequestMapping("/sdgdh456u4h5/fdhh156h87/grgfdfgdy1dfg1d5gw8358d7g/update")
 *  *     public void cronupdate(){
 *  *         activityService.updateCorn();
 *  *     }
 *  * 2. Quartz 定时任务框架
 *  * 使用方式参考 ：https://www.cnblogs.com/laoyeye/p/9352002.html
 *
 *
 *
 *
 *
 *
 * 编译 javac xx.java
 * 反编译 javap -c -p -v xx.class
 *      javap是jdk自带的反解析工具。它的作用就是根据class字节码文件，
 *  *    反解析出当前类对应的code区（汇编指令）、本地变量表、异常表和代码行偏移量映射表、 常量池等等信息
 * 反编译结果：
 * synchronized方法
 *  方法使用有ACC_SYNCHRONIZED标识
 *  如果是static方法，锁作用在类上（串行）
 *  如果非static方法，锁作用在具体类的对象上（并行）相互不影响
 *

 *
 *
 * @author: maxin
 * @create: 2020-03-06 16:34
 * @Modified By:
 * @Version: 1.0
 **/
public class SynchronizedTest {
    public static void main(String[] args) throws Exception {
        sellTickets();
        System.in.read();
    }

    private static int totalTickets = 10000;
    private static final Object LOCK = new Object();

    private static void sellTickets() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnableA = () -> {
            int threadATickets = 0;
            while (sellTicketsWithSyncMethod(1)) {
                threadATickets++;
            }
            System.out.println("Thread A buys " + threadATickets + " tickets");
        };

        Runnable runnableB = () -> {
            int threadBTickets = 0;
            while (sellTicketsWithSyncMethod(1)) {
                threadBTickets++;
            }
            System.out.println("Thread B buys " + threadBTickets + " tickets");
        };

        executorService.execute(runnableA);
        executorService.execute(runnableB);
    }

    /**
     * 方法级别使用synchronized关键字，synchronized方法
     *
     * @param count 卖票数量
     * @return 剩余票数是否足够
     */
    private static synchronized boolean sellTicketsWithSyncMethod(int count) {
        if (totalTickets - count < 0) {
            return false;
        } else {
            totalTickets = totalTickets - count;
            return true;
        }
    }

    /**
     * 某个对象级别使用synchronized关键字，synchronized代码块
     *
     * @param count 卖票数量
     * @return 剩余票数是否足够
     */
    private static boolean sellTicketsWithSyncObject(int count) {
        synchronized (LOCK) {
            if (totalTickets - count < 0) {
                return false;
            } else {
                totalTickets = totalTickets - count;
                return true;
            }
        }
    }
}
