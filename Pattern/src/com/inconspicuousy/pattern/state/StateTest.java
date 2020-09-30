package com.inconspicuousy.pattern.state;

// 状态模式（State Pattern）是指对有状态的对象，把复杂的判断逻辑提取到不同的状态对象中，允许状态对象在其内部状态发生变化时改变其行为。

// 什么是有状态的对象？
// 对象的某些属性与外部事件产生互动时会发生改变，从而使对象表现出不同的行为，这种影响对象行为的一个或者多个动态变化的属性称为状态，拥有状态属性的对象就是有状态对象。

// 相关角色
// 1、Context：上下文环境。负责维护一个当前状态，并将与状态相关的操作交给当前状态来处理。该类负责与客户端交互。简单来说， 该类就是有状态的对象。
// 2、State：抽象状态，用来声明特定状态下所对应的行为方法。
// 3、ConcreteState：具体状态对象，实现抽象状态所对应的行为。

//======================
// 当前我们模拟简单订单的状态扭转案例来演示状态模式。
// 默认下单之后才会产生订单，所以订单的初始状态为已下单（未付款）
// 订单的状态列表（已下单、已付款、已发货、已签收）

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 订单，负责状态的扭转
@Getter
@Setter
class Order {

    private String name;
    private State state;

    public Order(String name) {
        this.name = name;
        // 订单的初始状态为已下单
        this.state = new OrderState(this);
    }

    // 根据当前订单的状态处理一些事情
    public void doSomething() {
        // 本质上就是调用state对象去处理当前状态下的事情
        System.out.println("当前订单的状态为： " + state.getName());
        state.handler();
    }
}

// 状态接口
@Getter
@AllArgsConstructor
abstract class State {
    // 状态也会维护一个订单，方面获取订单的其他属性来处理状态下的行为
    private final Order order;
    // 状态的名称
    private final String name;
    // 定义订单状态所应的行为
    public abstract void handler();
}

// 已下单
class OrderState extends State {
    private static final String NAME = "已下单";
    public OrderState(Order order) {
        super(order, NAME);
    }
    @Override
    public void handler() {
        System.out.println(getOrder().getName() + "已下单，提醒买家付款");
    }
}

// 已付款
class PaidState extends State {
    private static final String NAME = "已付款";
    public PaidState(Order order) {
        super(order, NAME);
    }
    @Override
    public void handler() {
        System.out.println(getOrder().getName() + "已付款，提醒卖家发货");
    }
}

/**
 * 状态模式-测试
 * @author peng.yi
 */
public class StateTest {
    public static void main(String[] args) {

        Order order = new Order("苹果笔记本");
        order.doSomething();

        //    买家付款
        System.out.println("买家收到系统通知，已付款");
        order.setState(new PaidState(order));
        order.doSomething();

    }
}
