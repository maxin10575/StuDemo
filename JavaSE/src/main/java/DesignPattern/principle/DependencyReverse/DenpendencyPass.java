package DesignPattern.principle.DependencyReverse;

/**
 * @Author maxin
 * @Date 2019-11-12 11:37
 * @ClassName DenpendencyPass
 * @Description 依赖关系传递的三种方式
 * @Version 1.0
 **/
public class DenpendencyPass {

    public static void main(String[] args) {
        //1
       // OpenAndClose openAndClose = new OpenAndClose();
        //openAndClose.open(new Smallmi());
        //2
//        OpenAndClose1 openAndClose1 = new OpenAndClose1(new Chtv());
//        openAndClose1.open();
        //3
        OpenAndClose2 openAndClose2 = new OpenAndClose2();
        openAndClose2.setTv2(new HahaTV());
        openAndClose2.open();
    }

    //方式1：通过接口传递实现依赖
    interface IOpenAndClose{
        public void open(ITV itv); //抽象方法 接收接口
    }

    interface ITV {
        public void play();
    }

    static class OpenAndClose implements IOpenAndClose {
        @Override
        public void open(ITV itv) {
            itv.play();
        }
    }

    //使用
    static class Smallmi implements ITV{
        public void play(){
            System.out.println("小米电视，打开");
        }
    }


    //方式2：通过构造方法传递依赖
    interface IOpenAndClose1{
        public void open();
    }

    interface ITV1 {
        public void play();
    }

    static class OpenAndClose1 implements IOpenAndClose1 {
        public ITV1 itv1;
        public OpenAndClose1(ITV1 itv1){
            this.itv1 = itv1;
        }
        public void open(){
            this.itv1.play();
        }
    }
    //使用
    static class Chtv implements ITV1 {
        public void play(){
            System.out.println("CHTV电视，打开");
        }
    }

    //方式3：通过setter方法传递

    interface IOpenAndClose2{
        public void open();
        public void setTv2(ITV2 itv2);

    }

    interface ITV2 {
        public void play();
    }

    static class OpenAndClose2 implements IOpenAndClose2{
        private ITV2 itv2;
        public void setTv2(ITV2 itv2){
            this.itv2 = itv2;
        }
        public void open() {
            this.itv2.play();
        }
    }
    //使用
    static class HahaTV implements ITV2{
        public void play() {
            System.out.println("HahaTV电视，打开");
        }
    }
}
