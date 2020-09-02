package com.inconspicuousy.pattern.singleton.hungry.constant;


class Singleton {

    /** 采用静态常量的形式， 在类加载的时候直接进行实例化 */
    private static final Singleton SINGLETON = new Singleton();

    /** 构造器私有化，只能通过静态方法唯一入口实例化对象 */
    private Singleton() {
    }

    /** 直接返回定义的常量 */
    public static Singleton getInstance() {
        return SINGLETON;
    }
}


/**
 * 饿汉式实现方式之静态常量式
 * @author inconspicuousy
 */
public class StaticConstantExample {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        // true
        System.out.println(instance == instance1);
    }
}
