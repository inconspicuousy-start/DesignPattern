package com.inconspicuousy.pattern.observer;

// 观察者模式-发布订阅模式指的是 多个对象间存在一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。

// 发布者，管理订阅者，负责当自身状态发生改变时，通知订阅者作相应的改变。
// 订阅者也就是观察者， 发布者的状态发生改变时被通知并作相应的改变。
// 同一个订阅者也可以订阅多个发布者

// 这里我们公众号的场景。 微信用户订阅公众号， 当公众号发布消息时， 订阅者可以收到消息

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// 公众号发布的文章类
@Getter
@Setter
@AllArgsConstructor
class Message {
    private String content;
}

// 抽象的观察者： 微信用户
interface Observer {
    // 对接收到的消息做出响应
    void response(Message message);
}

// 抽象的发布者
interface Publisher {
    // 添加观察者
    void add(Observer observer);
    // 删除观察者
    void remove(Observer observer);
    // 通知所有的观察者处理消息
    void notifyObservers(Message message);
}

// 消息发布者
class ConcretePublisher implements Publisher {
    private final List<Observer> observers = new ArrayList<>();
    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        // 通知所有的接收者对消息做出响应
        observers.forEach(observer -> observer.response(message));
    }
}
// 实际的接收者
@EqualsAndHashCode
class ConcreteObserver implements Observer {
    // 微信用户名
    private final String name;
    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void response(Message message) {
        System.out.println("当前" + name + "接收到的消息为" + message.getContent());
    }
}
/**
 * 观察者模式-发布订阅模式-代码示例
 * @author peng.yi
 */
public class ObserverTest {
    public static void main(String[] args) {
        // 公众号A
        Publisher concretePublisherA = new ConcretePublisher();
        // 公众号B
        Publisher concretePublisherB = new ConcretePublisher();
        // 微信用户A
        ConcreteObserver concreteObserverA = new ConcreteObserver("微信用户A");
        // 微信用户B
        ConcreteObserver concreteObserverB = new ConcreteObserver("微信用户B");
        // 微信AB同时关注 公众号A
        concretePublisherA.add(concreteObserverA);
        concretePublisherA.add(concreteObserverB);
        // 微信A关注公众号B
        concretePublisherB.add(concreteObserverA);
        // 公众号A发布文章
        concretePublisherA.notifyObservers(new Message("我是公众号A发送的消息"));
        // 公众号B发布文章
        concretePublisherB.notifyObservers(new Message("我是公众号B发送的消息"));
        System.out.println("===================微信用户A取消关注公众号A");
        concretePublisherA.remove(concreteObserverA);
        // 公众号A发布文章
        concretePublisherA.notifyObservers(new Message("我是公众号A发送的消息"));

    }
}
