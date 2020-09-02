package com.inconspicuousy.pattern.singleton.lazy.sync;

class Singleton {

    /** 创建静态变量 */
    private static Singleton singleton = null;

    /** 构造器私有 */
    private Singleton() {
    }

    /** 利用synchronized关键字保证每次只会有一个线程访问到该类方法 */
    public synchronized static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}

/**
 * 利用synchronized关键字实现的懒汉式单例模式
 * @author inconspicuousy
 */
public class SyncExample {
    public static void main(String[] args) {
        // true
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
    }
}
