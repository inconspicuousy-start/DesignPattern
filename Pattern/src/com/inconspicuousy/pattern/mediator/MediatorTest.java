package com.inconspicuousy.pattern.mediator;

// 中介者模式： 定义一个中介对象来封装一系列对象之间的交互，使原有对象之间耦合松散，且可以独立的改变他们之间的交互。
// 中介者模式又叫调停模式

// 简单来说， 多个对象之间的相互交互， 交给中间人去传话处理，实现多个对象之间的解耦，直接交互变为间接交互。
// 独立改变：当交流的规则发生改变时， 我们只需要修改中间人传话规则就行。

// 我们就以买房子为例。
// 角色分为 房客、房东、中介
// 这里房客和房东借用 中介 这个中间人实现消息传递。

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// 抽象同事接口，因为同事类需要与中介者交互，所以需要与中介者建立依赖关系
// 提供将消息发送给中介者的方法
// 提供处理接收到其他消息的方法
@Getter
@Setter
@AllArgsConstructor
abstract class Collegua {
    // 与中介者建立依赖， 并可以设置处理的中介者
    private Mediator mediator;
    // 发送消息给中介者
    public abstract void send(String message);
    // 接收中介者的消息
    public abstract void receive(String message);
}

// 卖家
class Seller extends Collegua {

    public Seller(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String message) {
        // 将消息传递给中介进行转发
        this.getMediator().relay(this, message);
    }

    @Override
    public void receive(String message) {
        // 处理接收到的消息
        System.out.println("卖家收到消息： " + message);
    }
}

// 买家
class Buyer extends Collegua {
    public Buyer(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String message) {
        // 将消息传递给中介进行转发
        this.getMediator().relay(this, message);
    }

    @Override
    public void receive(String message) {
        System.out.println("买家收到消息： " + message);
    }
}


// 定义抽象中介者
// 因为涉及到多个对象之间的交互，所以需要提供管理对象的注册功能。
// 定义多个对象相互交互的抽象方法。
interface Mediator {
    void register(Collegua collegua);

    // 注意， 相互交互的话， 我们要知道交互的双方是谁以及交互的内容
    // collegua是话题发起人， message是传递的内容
    void relay(Collegua collegua, String message);
}

// 具体的中介
class ConcreteMediator implements Mediator {

    // 一般中介者只有只会有一个， 我们采用单例模式进行创建
    private static final ConcreteMediator CONCRETE_MEDIATOR = new ConcreteMediator();
    private ConcreteMediator() {
    }
    public static ConcreteMediator getInstance () {
        return CONCRETE_MEDIATOR;
    }

    // 中介一般会将买房的人信息和卖房的人分开管理
    // 卖房子
    private final List<Collegua> sellers = new ArrayList<>();
    // 买房子
    private final List<Collegua> buyers = new ArrayList<>();

    @Override
    public void register(Collegua collegua) {
        if (collegua instanceof Seller) {
            sellers.add(collegua);
        } else {
            buyers.add(collegua);
        }
    }

    @Override
    public void relay(Collegua collegua, String message) {
        if (collegua instanceof Seller) {
            buyers.forEach(buyer -> buyer.receive(message));
        } else {
            // 找到任意一个卖房的人
            sellers.stream().findAny().ifPresent(seller -> seller.receive(message));
        }
    }
}

/**
 * 中介者模式代码测试
 * @author peng.yi
 */
public class MediatorTest {
    public static void main(String[] args) {

        ConcreteMediator concreteMediator = ConcreteMediator.getInstance();
        Seller seller = new Seller(concreteMediator);
        // 获取到两个买房的人
        Buyer buyer = new Buyer(concreteMediator);
        Buyer buyer1 = new Buyer(concreteMediator);
        // 将买房的人和卖房的人的信息注册到系统
        concreteMediator.register(seller);
        concreteMediator.register(buyer);
        concreteMediator.register(buyer1);

        seller.send("我的房子又大又好又便宜，快来租");
        buyer.send("我要我要~");
    }
}
