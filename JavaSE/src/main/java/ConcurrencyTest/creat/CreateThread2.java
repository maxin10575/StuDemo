package ConcurrencyTest.creat;


public class CreateThread2 {

   /* public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
    }*/
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread running");
        }
    }

     static class JoinDemo extends Thread {
         int i;
         Thread previousThread; //上一个线程

         public JoinDemo(Thread previousThread, int i) {
             this.previousThread = previousThread;
             this.i = i;
         }

         @Override
         public void run() {
             try {
                 //调用上一个线程的join方法
                 previousThread.join();
             } catch (Exception e) { //Interrupted
                 e.printStackTrace();
             }
             System.out.println("num:" + i);
         }
     }
        public static void main(String[] args) {
            Thread previousThread=Thread.currentThread();
            for(int i=0;i<5;i++){
                JoinDemo joinDemo=new JoinDemo(previousThread,i);
                joinDemo.start();
                previousThread=joinDemo;
            }
        }


}
