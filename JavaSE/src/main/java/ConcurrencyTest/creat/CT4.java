package ConcurrencyTest.creat;

import org.junit.Test;

/**
 * @program: StuDemo
 * @description: 匿名内部类
 * @author: maxin
 * @create: 2020-03-30 12:51
 * @Modified By:
 * @Version: 1.0
 **/
public class CT4 {

    public static void main(String[] args) {
        //方式一的匿名内部类形式创建
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("你好");
                }
            }
        }.start();
        //方式二的匿名内部类形式创建
        new Thread(new Runnable() {
            public void run() {
                for (int j = 0; j < 10; j++) {
                    System.out.println("哈哈");
                }
            }
        }
        ).start();

    }

    public static void test() {
        //查看运行main方法的线程
        Thread t = Thread.currentThread();

        //查看线程名
        String name = t.getName();
        System.out.println(name);

        //查看唯一标示
        long id = t.getId();
        System.out.println(id);

        //查看优先级
        int priority = t.getPriority();
        System.out.println(priority);

        //是否处于活动状态
        boolean isAlive = t.isAlive();
        System.out.println(isAlive);

        //是否为守护线程
        boolean isDaemon = t.isDaemon();
        System.out.println(isDaemon);

        //是否被中断了
        boolean isInterrupted = t.isInterrupted();
        System.out.println(isInterrupted);
    }

    /**
     * 线程优先级
     * 线程对于线程的调度的工作是不可控的。线程只能被动
     * 的被分配时间片，不能主动获取。线程调度也尽可能
     * 的将时间片的次数均匀的分配给所有并发运行的线程。
     * 但是不保证“一人一次”。
     * 线程可以通过改变线程的优先级来改变获取CPU时间片的次数。
     * 理论上，线程优先级越高的线程，获取时间片的次数就越多。
     * 线程优先级有10个等级分别用1-10表示，1最小，10最大，5为默认值。
     * thread 提供了常量表示最大优先级，最小优先级与默认优先级，分别是
     * MAX_PRIORITY    MIN_PRIORITY   NORM_PRIORITY
     */
    public static void test2() {
        Thread max = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("max");
                }
            }
        };
        Thread min = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("min");
                }
            }
        };
        Thread nor = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("nor");
                }
            }
        };

        max.setPriority(Thread.MAX_PRIORITY);
        min.setPriority(1);

        min.start();
        nor.start();
        max.start();
    }

    /**
     * 守护线程，又称为后台线程
     * 使用上与前台线程一致。但是在结束时机上，有一个
     * 例外，即：进程结束时，会强制将运行的后台线程停止
     * 进程结束：当一个进程中的所有前台线程都结束了，那么
     * 进程就会结束。
     * 默认创建的线程都是前台线程，后台线程需要单独
     * 进行设置，线程提供了方法：
     * void setDaemon(booelan tf)
     * 若参数为true，则该线程为守护线程（后台线程）
     *
     * @author maxin
     */

    @Test
    public  void test3() {
        Thread rose = new Thread() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("rose:" + i + "啊啊啊啊");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread jack = new Thread() {
            public void run() {
                for (int i = 0; i < 8; i++) {
                    System.out.println("jack" + i + "hahah");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        jack.setDaemon(true); //-----------------守护线程方法
        rose.start();
        jack.start();
    }
}
