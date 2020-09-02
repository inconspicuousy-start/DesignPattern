package com.inconspicuousy.pattern.singleton.lazy.inner;

import java.util.stream.IntStream;

class Singleton {

    /** 私有构造器 */
    private Singleton() {
    }

    /** 采用静态内部类的方式， 只有当内部类被加载时实力才会被创建， 实现了懒加载 */
    private static class SingletonInstance {
        private static final Singleton SINGLETON = new Singleton();
        public static Singleton getInstance () {
            return SINGLETON;
        }
    }

    /** 只有调用当前方法时， 静态内部类才会被加载 */
    public static  Singleton getInstance() {
        return SingletonInstance.getInstance();
    }
    
}

/**
 * 采用懒汉式静态内部类实现的单例模式
 * @author inconspicuousy
 */
public class InnerClassExample {
    public static void main(String[] args) {
        // 所有的线程返回值一致
        IntStream.rangeClosed(1,10).boxed().forEach(i -> {
            new Thread(() -> System.out.println(Singleton.getInstance().hashCode())).start();
        });
    }
}
