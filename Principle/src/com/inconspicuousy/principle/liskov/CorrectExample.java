package com.inconspicuousy.principle.liskov;

class A {
    public int method1(int a, int b) {
        return a + b;
    }
    public void method2() {
        System.out.println("B类想要使用的方法");
    }
}

class B {

    // 通过`组合`的方式引入A类, 想要使用A类的方法时, 直接通过a对象调用即可
    private static final A a = new A();

    public int method1(int a, int b) {
        // 调用B类想要调用的方法
        B.a.method2();
        // 返回两数只差
        return a - b;
    }
}

/**
 * 正确案例
 * @author peng.yi
 */
public class CorrectExample {
    public static void main(String[] args) {
        B b = new B();
        // 返回两数只差
        int i = b.method1(1, 2);
        // i = -1
        System.out.println("i = " + i);
    }
}
