package com.inconspicuousy.principle.ocp.error;

/** 基类 */
abstract class AbstractShape {
    /** 形状的类型 */
    int type;
}
/** 圆形 */
class Circle extends AbstractShape {

    public Circle() {
        this.type = 1;
    }
}

/** 三角形 */
class Triangle extends AbstractShape {
    public Triangle() {
        this.type = 2;
    }
}

// 使用方
class Graphic {

    private void drawCircle() {
        System.out.println("画圆形");
    }

    private void drawTriangle () {
        System.out.println("画三角形");
    }

    /** 根据shape的类型画出不同的形状 */
    public void draw(AbstractShape shape) {
        switch (shape.type) {
            case 1:
                drawCircle();
                return;
            case 2:
                drawTriangle();
                return;
            default:

        }
    }

}

/**
 * 开放关闭原则错误案例
 * @author peng.yi
 */
public class OcpErrorExample {

    public static void main(String[] args) {
        Graphic graphic = new Graphic();
        // 画圆形
        graphic.draw(new Circle());
        // 画三角形
        graphic.draw(new Triangle());
    }

}
