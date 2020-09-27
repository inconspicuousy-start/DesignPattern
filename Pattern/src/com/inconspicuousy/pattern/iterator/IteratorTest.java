package com.inconspicuousy.pattern.iterator;

import java.util.*;

/**
 * 迭代器模式测试
 * @author peng.yi
 */
public class IteratorTest {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);

        // 创建ArrayList对象
        List<Integer> arrayList = new ArrayList<>(integers);
        // 创建LinkedList对象
        List<Integer> linkedList = new LinkedList<>(integers);

        // 在这里我们可以看到, 不管聚合对象的集合属性是什么类型, 我们都可以用统一的方法进行处理
        // 相同的方法提供迭代器对象, 利用迭代器对象实现对集合的操作.
        handlerIterator(arrayList);
        handlerIterator(linkedList);
    }

    // 统一处理集合对象
    private static void handlerIterator(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        iterator.forEachRemaining(System.out::println);
    }


}
