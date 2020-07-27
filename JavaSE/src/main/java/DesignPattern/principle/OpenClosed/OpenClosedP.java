package DesignPattern.principle.OpenClosed;

/**
 * @Author maxin
 * @Date 2019-11-15 11:52
 * @ClassName OpenClosedP
 * @Description 开闭原则，1.一个软件实体如类，模块和函数应该对扩展开放（对提供方），对修改关闭
 * （对使用方）。用抽象构建框架，用实现扩展细节
 * 2。当软件需要变化时，尽量通过扩展软件实体的行为来实现变化，而不是通过修改已有的代码来实现变化
 * 3. 编程中遵循其他原则，以及使用设计模式的目的就是遵循 开闭原则
 * @Version 1.0
 **/
public class OpenClosedP {
    public static void main(String[] args) {
        //1
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
    }

}

//方式1 ： 增加一个三角形，需要修改的代码很多
//这是一个用于绘图的类 【使用方】
class GraphicEditor {
    //接收Shape对象，
    public void drawShape(Shape s) {
        if(s.m_type == 1){
            drawRectangle(s);
        }else if (s.m_type == 2 ){
            drawCircle(s);
        }
    }
    public void drawRectangle(Shape r) {
        System.out.println("绘制矩形");
    }
    public void drawCircle(Shape r ){
        System.out.println("绘制圆形");
    }
}

//基类
class Shape {
    int m_type;
}

class Rectangle extends Shape {
    Rectangle() {
        super.m_type = 1;
    }
}

class Circle extends Shape {
    Circle() {
        super.m_type = 2;
    }
}

