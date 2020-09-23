package com.inconspicuousy.pattern.bridge;

// 桥接模式主要就是针对具有多维度属性的对象
// 将其属性提取出来并进行抽象化成接口或者抽象类，再将这些接口以聚合的形式组合起来

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// 形状类
abstract class Shape {
    private Color color;
}

// 三角形
class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }
}

// 矩形
class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }
}


@Getter
@Setter
@AllArgsConstructor
// 颜色类， 提供全参数构造器
abstract class Color {
   private String color;
}

// 红颜色
class Red extends Color {
    public Red() {
        super("红色");
    }
}

class Green extends Color {
    public Green() {
        super("绿色");
    }
}

/**
 * 桥接模式测试类
 * @author peng.yi
 */
public class BridgeTest {
    public static void main(String[] args) {
        // 这里要创建 各个颜色的形状类, 我们进行形状和颜色的组合即可
        // 使用桥接模式， 将形状和颜色属性分别抽象化成接口, 我们就可以对形状和颜色随时进行扩展

        Color red = new Red();
        Color green = new Green();
        Rectangle rectangle = new Rectangle(red);
        Rectangle greenRectangle = new Rectangle(green);
        Triangle triangle = new Triangle(red);
        Triangle greenTriangle = new Triangle(green);
        System.out.println("rectangle = " + rectangle);
        System.out.println("greenRectangle = " + greenRectangle);
        System.out.println("triangle = " + triangle);
        System.out.println("greenTriangle = " + greenTriangle);
    }
}
