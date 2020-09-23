package com.inconspicuousy.pattern.decorator;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 被装饰者组件 （Component）
abstract class Drink {
    // 价格
    private Double price;
    // 描述
    protected String desc;
    // 计算费用由子类自己完成
    abstract public double cost();
}

// 实际的被装饰者 （ConcreteComponent）
class Coffer extends Drink {
    public Coffer() {
        this.setDesc("咖啡");
        this.setPrice(15.0);
    }

    @Override
    public double cost() {
        return this.getPrice();
    }
}

// 装饰器
// 注意， 装饰器必须也继承或者实现 被装饰者抽象类或者接口
// 对客户端而言， 并不会觉得对象在装饰前和装饰后有什么区别
class Decorator extends Drink{
    // 装饰器采用的是继承+组合的形式， 因为涉及到对原有对象的功能进行扩展
    private Drink drink;
    public Decorator(Drink drink) {
        this.drink = drink;
    }
    @Override
    public double cost() {
        // 当前装饰器的价格 + 原有对象的价格
        return this.getPrice() + drink.cost();
    }

    @Override
    public String getDesc() {
        return desc + "_" + drink.getDesc();
    }
}

class Chocolate extends Decorator {
    // 创建Chocolate对象时你就必须明确当前的价格
    public Chocolate(Drink drink) {
        super(drink);
        setDesc("巧克力");
        setPrice(1.0);
    }
}

class Milk extends Decorator {
    // 创建牛奶对象时你就必须明确当前的价格
    public Milk(Drink drink) {
        super(drink);
        setDesc("牛奶");
        setPrice(2.0);
    }
}


/**
 * 装饰者模式-咖啡牛奶代码示例-测试
 * @author peng.yi
 */
public class DecoratorTest {
    public static void main(String[] args) {

        // 被装饰者对象
        Drink coffer = new Coffer();
        System.out.println(coffer.getDesc());
        System.out.println(coffer.cost());

        // 加牛奶
        coffer = new Milk(coffer);
        System.out.println(coffer.getDesc());
        System.out.println(coffer.cost());

        // 加巧克力
        coffer = new Chocolate(coffer);
        System.out.println(coffer.getDesc());
        System.out.println(coffer.cost());

    }
}
