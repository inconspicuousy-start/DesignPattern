package com.inconspicuousy.pattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * @author peng.yi
 */
public class ArrayCloneTest {

    public static void main(String[] args) {
        Person[] persons = new Person[]{new Person("1"), new Person("2")};
        System.out.println(Arrays.toString(persons));
        // T[] 数组本身是实现了Cloneable接口的， 这里的T可以是引用数据类型（即使没有实现Cloneable接口），也可以是主要的数据类型
        Person[] clone = persons.clone();
        System.out.println("clone = " + Arrays.toString(clone));
    }
}

@Getter
@Setter
@AllArgsConstructor
class Person {
    private String  name;
}
