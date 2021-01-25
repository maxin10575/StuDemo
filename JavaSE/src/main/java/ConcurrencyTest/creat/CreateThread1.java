package ConcurrencyTest.creat;


public class CreateThread1 {


    public static void main(String[] args) {
   /*     while (flag) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    //
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }*/
    }

    private   boolean flag = true;

    public void test(){
        while (flag) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                //提交offset
                    // 提交offset成功。立马新建线程
                 //处理数据
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

}
