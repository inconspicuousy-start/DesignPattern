package com.inconspicuousy.pattern.adapter;

// 接口适配器使用场景： 一般不想实现所有的接口的方法时， 就需要一个适配器抽象类去实现该接口
// 被适配目标为接口
interface A {
    void method1();
    void method2();
    void method3();
}

// 定义一个抽象类实现接口A，但是方法都为空实现
// 当我们需要使用适配器对象时， 直接采用匿名类的方式创建适配器抽象类对象，需要使用哪一个方法就重写哪一个方法即可
abstract class AdapterAbstractB implements A {
    @Override
    public void method1() { }

    @Override
    public void method2() { }

    @Override
    public void method3() { }
}

/**
 * 接口适配器（默认的适配器）
 * @author peng.yi
 */
public class InterfaceAdapterTest {

    public static void main(String[] args) {
        // 当我们需要使用适配器对象时， 直接采用匿名类的方式创建适配器抽象类对象，
        // 需要使用哪一个方法就重写哪一个方法即可， 就可以不用管其他的方法
        AdapterAbstractB adapterAbstractB = new AdapterAbstractB() {
            @Override
            public void method1() {
                System.out.println("我只想执行方法1");
            }
        };
        adapterAbstractB.method1();
    }
}
