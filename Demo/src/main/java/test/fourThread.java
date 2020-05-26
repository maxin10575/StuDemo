package test;

/**
 * @Author maxin
 * @Date 2020-01-03 08:51
 * @ClassName fourThread
 * @Description \\
 * @Version 1.0
 **/
public class fourThread {
    private int j ;
    private class Add implements Runnable{
        public void run(){
            add();
        }
    }
    private class Sub implements Runnable{
        public void run(){
            sub();
        }
    }
    private synchronized  void add() {
        j++;
    }
    private synchronized void sub() {
        j--;
    }

    public static void main(String[] args) {
        fourThread fourThread = new fourThread();
        Add add = fourThread.new Add();
        Sub sub = fourThread.new Sub();
        for(int i =0;i<2;i++){
            Thread thread = new Thread(add);
            thread.start();
            thread = new Thread(sub);
            thread.start();
        }
    }
}