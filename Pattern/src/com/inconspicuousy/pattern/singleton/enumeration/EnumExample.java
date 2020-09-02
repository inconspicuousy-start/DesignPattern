package com.inconspicuousy.pattern.singleton.enumeration;

/**
 * 利用枚举类创建对象
 *
 * 其实枚举的底层创建的实例都是被 static final修饰的
 * 本质上是一种饿汉式的特殊形式
 * */
enum Singleton {
    SINGLETON;
}

/**
 * 利用枚举类实现单例模式
 *
 * @author inconspicuousy
 */
public class EnumExample {
    public static void main(String[] args) {
        // true
        System.out.println(Singleton.SINGLETON == Singleton.SINGLETON);
    }
}
