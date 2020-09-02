package com.inconspicuousy.pattern.singleton.lazy.check;

import java.util.stream.IntStream;

class Singleton {

    /**
     * 这里采用 volatile 关键字修饰singleton是因为防止某个线程取得是缓存数据创建多个实例
     *
     * 被volatile关键字修饰之后， 线程直接会与内存交互， 对其他线程修改的数据是可见的， 每次线程获取singleton的值时都是直接与内存交互
     * */
    private volatile static Singleton singleton = null;

    /** 构造器私有化 */
    private Singleton() {
    }

    /**
     * 这里放弃使用synchronized修饰方法的是因为singleton被实例化就会触发一次， 每次都要锁方法影响程序的性能
     *
     * 将synchronized关键字提到只有当singleton为空时才会锁住， 增强程序的性能
     * */
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                // 这里再次对singleton作判空处理是因为防止某些线程已经读取到singleton为空的情况下， 进入阻塞状态， 解除阻塞后实例化singleton
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

/**
 * 双重检查实现懒汉式单例模式
 * @author inconspicuousy
 */
public class DoubleCheckExample {

    public static void main(String[] args) {

        // 十个线程获取的值一致
        IntStream.rangeClosed(1,10).boxed().forEach(i -> {
            new Thread( () -> {
                System.out.println(Singleton.getInstance().hashCode());
            }).start();
        });
    }


}
