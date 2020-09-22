package com.inconspicuousy.pattern.prototype.deepcopy;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peng.yi
 */
public class DeepCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Deep deep = new Deep();
        DeepField deepField = new DeepField();
        deepField.setName("123");
        deep.setDeepField(deepField);

        System.out.print("原始对象的引用数据类型字段：");
        System.out.println(deep.getDeepField());

        Deep clone = (Deep) deep.clone();
        System.out.print("克隆对象的引用数据类型字段：");
        System.out.println(clone.getDeepField());

        System.out.print("原始对象改变属性值前，克隆对象的引用数据类型对象所对应的值： ");
        System.out.println(clone.getDeepField().getName());
        deep.getDeepField().setName("456");
        System.out.print("原始对象改变属性值后，克隆对象的引用数据类型对象所对应的值： ");
        System.out.println(clone.getDeepField().getName());
    }

}

@Getter
@Setter
// 实现Cloneable接口重写clone方法
class Deep implements Cloneable {
    private DeepField deepField;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Deep clone = (Deep) super.clone();
        // 在clone类中需要对所有的引用数据类型进行单独的clone并赋值
        DeepField deepField = (DeepField) this.deepField.clone();
        clone.setDeepField(deepField);
        return clone;
    }
}


@Getter
@Setter
// 对于所有的被clone的类都实现Clone接口， 并并写clone方法
class DeepField implements Cloneable{
    private String name;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
