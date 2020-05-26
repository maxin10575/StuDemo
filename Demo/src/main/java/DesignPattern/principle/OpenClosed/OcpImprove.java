package DesignPattern.principle.OpenClosed;

/**
 * @Author maxin
 * @Date 2019-11-15 16:33
 * @ClassName OcpImprove
 * @Description OCP 解决方案
 * @Version 1.0
 **/
public class OcpImprove {
    public static void main(String[] args) {
        GraphicEditor1 graphicEditor = new GraphicEditor1();
        graphicEditor.drawShape(new Rectangle1());
        graphicEditor.drawShape(new Circle1());
    }
}

class GraphicEditor1 {
    public void drawShape(Shape1 s) {
        s.draw();
    }
}

abstract class Shape1 {
    public abstract void draw();
}

class Rectangle1 extends Shape1 {
    public void draw(){
        System.out.println("绘制矩形");
    }
}

class Circle1 extends Shape1 {
    public void draw(){
        System.out.println("绘制圆形");
    }
}