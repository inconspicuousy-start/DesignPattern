package com.inconspicuousy.pattern.memento;

// 备忘录模式又叫快照模式。
// 备忘录模式就是程序中的后悔药。
// 备忘录模式指的是在不破坏代码的封装性的前提下， 捕获一个对象的内部状态， 并在该对象之外保存这个状态， 以便以后当需要的时候能将该对象恢复到原先保存的状态。

// 相关角色
// 1、Originator(发起人): 需要保存状态的对象
// 2、Memento（备忘录）：备忘录对象， 用于保存对象状态的对象。
// 3、Caretaker（管理者）： 管理备忘录对象的对象， 提供保存和获取备忘录状态的功能，但是其不能对备忘录的内容进行访问和修改。
// =======================================================

// 当前以游戏中角色为例， 角色参加某个活动前保存角色正常的攻击属性，活动结束后，恢复到正常水平

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
// 涉及到集合重写equals方法hashcode方法
@EqualsAndHashCode
// 备忘录状态
class Memento {
    // 角色id
    private String originatorId;
    // 保存角色的攻击力
    private int attack;
}

@Getter
@Setter
@AllArgsConstructor
@ToString
// 角色信息
class Originator {
    private String id;
    private String name;
    private int attack;

    // 保存状态
    // 创建备忘录交给管理备管理对象进行处理
    public Memento saveAttack() {
        System.out.println(name + "说：我要参加活动了，把我当前的状态的状态保存一下");
        return new Memento(this.id, this.attack);
    }

    // 状态还原
    // 从备忘录中取出状态
    public void restore(Memento mementoc) {
        System.out.println(name + "说：活动结束了，把我以前的状态还给我吧");
        this.attack = mementoc.getAttack();
    }

}

// 管理备忘录状态， 参与保存和获取备忘录状态， 但是不参与修改和访问备录的内容
class Caretaker {
    private static final HashMap<String, Memento> MEMENTO_HASH_MAP = new HashMap<>();

    // 保存备忘录对象
    public void saveMemento(Memento memento) {
        MEMENTO_HASH_MAP.put(memento.getOriginatorId(), memento);
    }

    // 获取备忘录对象
    public Memento getMementTo(String originatorId) {
        return MEMENTO_HASH_MAP.get(originatorId);
    }

}

/**
 * 备忘录模式测试
 * @author peng.yi
 */
public class MementoTest {

    public static void main(String[] args) {
        // 创建两个角色
        Originator zs = new Originator("1", "张三", 100);
        Originator ls = new Originator("2", "李四", 98);

        // 管理备忘录状态
        Caretaker caretaker = new Caretaker();

        System.out.println("===参加活动前角色信息===");
        System.out.println("zs = " + zs);
        System.out.println("ls = " + ls);
        // 参加活动、
        System.out.println("===参加活动后角色信息===");
        caretaker.saveMemento(zs.saveAttack());
        caretaker.saveMemento(ls.saveAttack());
        zs.setAttack(200);
        ls.setAttack(198);
        System.out.println("zs = " + zs);
        System.out.println("ls = " + ls);

        // 参加完活动
        System.out.println("===活动结束后角色信息===");
        zs.restore(caretaker.getMementTo(zs.getId()));
        ls.restore(caretaker.getMementTo(ls.getId()));
        System.out.println("zs = " + zs);
        System.out.println("ls = " + ls);


    }
}
