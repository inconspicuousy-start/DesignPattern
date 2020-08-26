package com.inconspicuousy.principle.ocp.correct;

/** 抽象形状类 */
abstract class AbstractShape {
    abstract void draw();
}

class Circle extends AbstractShape{
    @Override
    void draw() {
        System.out.println("画圆形");
    }
}

class Triangle extends AbstractShape{
    @Override
    void draw() {
        System.out.println("画三角形");
    }
}

class Graphic {
    // 对于使用方, 直接调用shape对应的画图方法即可
    public void draw(AbstractShape shape) {
        shape.draw();
    }
}

/**
 * ocp原则正确示例
 * @author peng.yi
 */
public class OcpCorrectExample {
    public static void main(String[] args) {
        Graphic graphic = new Graphic();
        graphic.draw(new Circle());
        graphic.draw(new Triangle());
    }
}
