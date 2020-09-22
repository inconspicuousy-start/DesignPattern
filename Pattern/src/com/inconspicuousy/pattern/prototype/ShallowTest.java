package com.inconspicuousy.pattern.prototype;

import lombok.Getter;
import lombok.Setter;

/**
 * 浅拷贝测试
 * @author peng.yi
 */
public class ShallowTest {
    public static void main(String[] args) throws Exception{

        Shallow shallow = new Shallow();
        ShallowField shallowField = new ShallowField();
        shallowField.setName("123");

        shallow.setShallowField(shallowField);

        System.out.println(shallow.getShallowField());

        // 对象本身与克隆出来的对象的引用数据类型都是指向同一个地址值， 浅表复制
        Shallow shallow1 = (Shallow) shallow.clone();
        System.out.println("克隆后引用数据类型值");
        System.out.println(shallow1.getShallowField());

        // 当改变原始对象的引用数据类型的值时， 克隆出来的引用数据类型的值也会发生变化
        System.out.println(shallow1.getShallowField().getName());
        // 改变原始对象的引用数据类型的值
        shallow.getShallowField().setName("456");
        // 克隆出来的引用数据类型的值也会发生变化
        System.out.println(shallow1.getShallowField().getName());
    }
}

@Getter
@Setter
class Shallow implements Cloneable{
    private ShallowField shallowField;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Getter
@Setter
class ShallowField {
    private String name;
}
