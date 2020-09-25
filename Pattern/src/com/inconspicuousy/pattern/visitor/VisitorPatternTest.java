package com.inconspicuousy.pattern.visitor;

// 访问者模式（Visitor Pattern）
// 主要就是指有多种不同的处理方式处理一个对象中集合属性中的元素，此时这里不同的处理方式抽象出来就是访问者，
// 每个访问者对于属性中的元素都有自己不同的理解和处理方式
// 注意，集合中属性的元素的种类要是确定的，因为对于访问者来说， 我们要针对每一种元素有不同的处理方式。
// 但是对于元素的个数不确定的，集合可以有一个种类的多个元素。

// 这里我们举游乐场的例子来说明， 游乐场中有多种不同的游乐玩具，对于游客来说有分大小和小孩，大人和小孩对于游乐场中的玩具有自己的不同的理解。
// 这里游乐园中的玩具的种类是确定的，我们假定有旋转木马和太阳车， 但是我们可以有很多个旋转木马和太阳车。

import lombok.EqualsAndHashCode;
import sun.security.provider.Sun;

import java.util.ArrayList;
import java.util.List;

// 游客
interface Visitor {
    //    针对不同的玩具有不同的处理方式
    // 旋转木马
    void visitCarousel(Carousel carousel);
    // 太阳车
    void visitSunCar(SunCar sunCar);
}

// 成年人
class Adult implements Visitor {
    //    针对不同的玩具有不同的处理方式
    @Override
    public void visitCarousel(Carousel carousel) {
        System.out.println("Adult当前玩的是： " + carousel + "表示很无趣");
    }

    @Override
    public void visitSunCar(SunCar sunCar) {
        System.out.println("Adult当前玩的是： " + sunCar + "表示很刺激");
    }
}

// 小孩子
class Children implements Visitor {
    //    针对不同的玩具有不同的处理方式
    @Override
    public void visitCarousel(Carousel carousel) {
        System.out.println("Children当前玩的是： " + carousel + "表示很好玩。");
    }

    @Override
    public void visitSunCar(SunCar sunCar) {
        System.out.println("Children当前玩的是： " + sunCar + "表示很可怕，再也不想玩了");
    }
}

// 玩具类(游乐场中的玩具)
interface Mathine {
    // 定义访客玩玩具
    void play(Visitor visitor);
}

// 注意集合中的元素必须重写equals方法
@EqualsAndHashCode
class Carousel implements Mathine{
    @Override
    public void play(Visitor visitor) {
        visitor.visitCarousel(this);
    }
}
// 注意集合中的元素必须重写equals方法
@EqualsAndHashCode
class SunCar implements Mathine{
    @Override
    public void play(Visitor visitor) {
        visitor.visitSunCar(this);
    }
}

// 游乐园
class Playground {
    private final List<Mathine> mathines = new ArrayList<>();

    // 打印出一个用户玩完所有的玩具的感觉
    public void play(Visitor visitor) {
        mathines.forEach(m -> m.play(visitor));
    }
    // 添加玩具
    public void addMathine(Mathine mathine) {
        mathines.add(mathine);
    }

    public void removeMathine(Mathine mathine) {
        mathines.remove(mathine);
    }

}

/**
 * 访问者模式- 游乐代码示例
 * @author peng.yi
 */
public class VisitorPatternTest {
    public static void main(String[] args) {
        Carousel carousel = new Carousel();
        SunCar sunCar = new SunCar();
        Playground playground = new Playground();
        // 集合中元素的种类确定， 但是元素的个数时不确定的
        playground.addMathine(carousel);
        playground.addMathine(sunCar);
        playground.addMathine(sunCar);
        // 打印成年的感受
        Adult adult = new Adult();
        playground.play(adult);
        System.out.println("==============");
        // 打印小孩子的感受
        Children children = new Children();
        playground.play(children);
    }
}
