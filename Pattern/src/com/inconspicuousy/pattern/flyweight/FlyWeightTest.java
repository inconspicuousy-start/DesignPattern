package com.inconspicuousy.pattern.flyweight;

// 享元模式-共享篮球示例
// 体育课上，老师篮球框中带来的球是固定的，每个学生拿到后用完就要及时归还，下一个学生就可以使用。
// 当前篮球被标上序号（篮球的个数为10分别为0-9）， 对应学号的尾数对应所玩的篮球。
// 此时对于共享对象篮球来说，玩的对象就是外部状态非共享， 篮球的各个属性就是共享状态，比如当前篮球的编号。。

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

// 外部状态对象学生类
@Getter
@Setter
@AllArgsConstructor
class Student {
    private int no;
    private String name;
}

@Getter
@Setter
abstract class Ball {
    // 共享内部状态
    private int number;
    public Ball(int number) {
        this.number = number;
    }
    // 抽象方法，
    // Student是外部状态
    abstract public void play(Student student);
}

// 篮球， 实际共享的状态
@ToString
class Basketball extends Ball {
    public Basketball(int number) {
        super(number);
    }

    @Override
    public void play(Student student) {
        System.out.println("当前篮球的编号为：" + getNumber() + ", 所属学生为：" + student.getName());
    }
}

// 享元简单工厂类， 用于管理享元对象
class BallFactory {
    // map集合存放篮球享元对象
    private static final Map<Integer, Ball> BALL_MAP = new HashMap<>(10);
    // 初始化时就要将篮球放入到篮球框中
    static {
        IntStream.rangeClosed(0,9).forEach(i -> {
            BALL_MAP.put(i, new Basketball(i));
        });
    }

    // 根据学号获取到对应的篮球
    public static Ball getBaskball(int studentNo) {
        int number = studentNo % 10;
        if (BALL_MAP.containsKey(number)) {
            // 对应的篮球还在篮球框中
            Ball ball = BALL_MAP.get(number);
            BALL_MAP.remove(number);
            return ball;
        }
        System.out.println("当前篮球已经被借出去了, 等待上一个同学归还后再来吧！");
        return null;
    }
}



/**
 * 享元模式- 共享篮球示例代码
 * @author peng.yi
 */
public class FlyWeightTest {
    public static void main(String[] args) {

        // 对于享元模式主要就是搞懂多个对象共享的问题。
        // 一般都会涉及到一个工厂类(池)存储共享对象
        Student student = new Student(5, "张三");
        Student lisi = new Student(15, "李四");
        // 张三先去借球
        Ball baskball = BallFactory.getBaskball(5);
        System.out.println(baskball);
        if (baskball != null) {
            baskball.play(student);
        }
        // 李四去借球
        Ball baskball1 = BallFactory.getBaskball(15);
        System.out.println(baskball1);
        if (baskball1 != null) {
            baskball1.play(lisi);
        }
    }

}
