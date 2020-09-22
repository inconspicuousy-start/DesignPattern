package com.inconspicuousy.pattern.prototype.deepcopy;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

/**
 * @author peng.yi
 */
public class SerializableTest {
    public static void main(String[] args) {
        SerializableClone deep = new SerializableClone();
        SerializableCloneFiled deepField = new SerializableCloneFiled();
        deepField.setName("123");
        deep.setDeepField(deepField);

        System.out.print("原始对象的引用数据类型字段：");
        System.out.println(deep.getDeepField());

        SerializableClone clone = deep.deepClone();
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
class SerializableClone implements Serializable {
    private SerializableCloneFiled deepField;

    // 利用序列化实现深拷贝
    public SerializableClone deepClone() {
        // 实现了Serializable接口就可以将对象进行序列化操作
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out)
        ) {
            // 序列化将当前这个对象以对象流的形式存储到Byte数组中
            oos.writeObject(this);
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()))) {
                // 反序列化将Byte数组中的数据读取出来
                return (SerializableClone) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

@Getter
@Setter
class SerializableCloneFiled implements Serializable {
    private String name;
}
