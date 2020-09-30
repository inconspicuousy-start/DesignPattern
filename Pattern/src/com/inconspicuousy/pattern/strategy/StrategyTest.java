package com.inconspicuousy.pattern.strategy;

// 策略模式（Strategy Pattren）是指封装一系列算法使他们之间可以相互替换，且算法的变化不会影响使用算法的客户。

// 简单来说， 就是将用户某类行为抽象化成接口， 并提供一系列的实现。用来给用户赋予不同的行为属性，用户就表现出不同的行为。
// 举例来说，旅行可以有坐飞机、坐动车等，做螃蟹可以有清蒸、红烧等

// 相关角色
// 1、Context： 上下文， 持有策略类的引用，也就是该用户的某类行为被抽象出来的。
// 2、Strategy： 抽象策略接口， 用来定义抽象策略方法。
// 3、ConcreteStrategy： 实际的策略类， 实现抽象策略。

//=================================
// 当前实现旅行可以做飞机，坐动车等策略

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 抽象的策略
interface Strategy {
    void run();
}

// 实际的旅行策略(船)
class ShipStrategy implements Strategy {
    @Override
    public void run() {
        System.out.println("当前采用的旅行方式为船");
    }
}
// 实际的旅行策略(飞机)
class Airplane implements Strategy {
    @Override
    public void run() {
        System.out.println("当前采用的旅行方式为飞机");
    }
}

@Getter
@Setter
@AllArgsConstructor
class Person {
    // 姓名
    private String name;
    // 旅行策略
    private Strategy strategy;

    // 旅游
    public void travel() {
        strategy.run();
    }
}
/**
 * 策略模式代码测试
 * @author peng.yi
 */
public class StrategyTest {

    public static void main(String[] args) {

        // 采用船旅游
        Person person = new Person("张三", new ShipStrategy());
        person.travel();

        // 采用飞机旅游
        person.setStrategy(new Airplane());
        person.travel();
    }
}
