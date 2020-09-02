package com.inconspicuousy.pattern.singleton.hungry.block;

class Singleton {

    /** 注意， 这里为什么能够用final修饰， 是因为所有的静态代码都会在类构造器中合成一个方法执行  */
    private static final Singleton singleton;

    /* 通过静态代码块进行初始化 */
    static {
        singleton = new Singleton();
    }

    /** 构造器私有化 */
    private Singleton() {
    }

    /** 获取对象入口 */
    public static Singleton getInstance() {
        return singleton;
    }
}

/**
 * 饿汉式实现方式之静态代码块
 * @author inconspicuousy
 */
public class BlockCodeExample {

    public static void main(String[] args) {
        // true
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
    }

}
