package ConcurrencyTest;

/**
 * @program: studying
 * @description: 多线程之间共享主内存变量
 * @author: maxin
 * @create: 2020-03-06 16:08
 * @Modified By:
 * @Version: 1.0
 **/
public class ThreadSharedVariables {
    private static int a = 0;

    public static void main(String[] args) throws Exception {

        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " a = " + a);
            a = 1;
            try {
                Thread.sleep(1000L);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " a = " + a);
        }, "ThreadA");

        Thread threadB = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " a = " + a);
//            a = 1;
            try {
                Thread.sleep(1000L);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " a = " + a);
        }, "ThreadB");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("a = " + a);
    }

}
