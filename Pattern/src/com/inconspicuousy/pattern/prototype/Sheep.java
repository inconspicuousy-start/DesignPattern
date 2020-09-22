package com.inconspicuousy.pattern.prototype;

import lombok.*;

/**
 * 原型模式
 * Java中类只需要实现Cloneable接口就相当于作了标记， 该类对象重写父类的clone方法
 * @author peng.yi
 */
@Getter
@Setter
@AllArgsConstructor
public class Sheep implements Cloneable {
    private String name;

    @Override
    public Object clone() throws CloneNotSupportedException {
        // 这里我们重写的父类方法， 直接调用父类的方法即可， 只是改变了方法的访问权限
        return super.clone();
    }

}

class SheepTest {
    public static void main(String[] args) throws Exception{

        Sheep sh = new Sheep("苏恩");
        System.out.println("sh = " + sh);
        Sheep sheep = (Sheep) sh.clone();
        // Object类的clone方法返回的是全新对象
        System.out.println("sheep = " + sheep);
        System.out.println(sheep.equals(sh));
    }
}
