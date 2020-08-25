package com.inconspicuousy.principle.liskov;

/** 定义基类 */
class Super {
    /** 定义一个方法实现两个整数相加 */
    public int method1(int a, int b) {
        return a + b;
    }

    public void method2() {
        System.out.println("子类想要使用的方法");
    }
}

/** 子类为了能够使用父类方法,直接继承父类 */
class Sub extends Super{
    /** 重写父类的方法, 实现两个整数相间 */
    @Override
    public int method1(int a, int b) {
        method2();
        return a - b;
    }
}

/**
 * 里氏替换原则-错误案例
 * @author peng.yi
 */
public class ErrorExaple {

    public static void main(String[] args) {
        Super s = new Sub();
        // 在用户使用过程中, 期待的结果就是和父类描述的一样, 返回两个整数之和
        // 结果打印之后返回的是两数只差
        int i = s.method1(1, 2);
        System.out.println(i);
    }

}

