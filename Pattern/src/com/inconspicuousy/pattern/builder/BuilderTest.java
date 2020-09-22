package com.inconspicuousy.pattern.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 产品类， 房子
 * 房子由： 地基， 墙高， 屋顶高组成
 * @author peng.yi
 */
@Getter
@Setter
class House {

    private int base;
    private int wall;
    private int roof;
}

// 房子抽象建造者
abstract class HouseBuilder {
    protected House house = new House();
    // 打地基
    public abstract void buildBasic(int basic);

    // 建造房子墙
    public abstract void buildWall(int wall);

    // 建造房顶
    public abstract void buildRoof(int roof);

    // 返回最后的构建对象对象
    public House builder() {
        return house;
    }
}

// 具体的普通房子建造器
class CommonHouseBuilder extends HouseBuilder{
    @Override
    public void buildBasic(int basic) {
        System.out.println("普通房子打地基");
        System.out.println("basic = " + basic);
        house.setBase(basic);
    }

    @Override
    public void buildWall(int wall) {
        System.out.println("普通房子打墙");
        System.out.println("wall = " + wall);
        house.setWall(wall);
    }

    @Override
    public void buildRoof(int roof) {
        System.out.println("普通房子建房顶");
        System.out.println("roof = " + roof);
        house.setRoof(roof);
    }
}


// 指挥者， 直接与客户交互的类
@Setter
@Getter
// 提供有参构造器
@AllArgsConstructor
class Director {
    private HouseBuilder houseBuilder;
    private int base;
    private int wall;
    private int roof;
    // 通过constructHouse方法产生对应的对象
    public House constructHouse() {
        houseBuilder.buildBasic(base);
        houseBuilder.buildWall(wall);
        houseBuilder.buildRoof(roof);
        return houseBuilder.builder();
    }
}

public class BuilderTest {
    public static void main(String[] args) {
        // 获取到具体的建造器
        HouseBuilder commonHouseBuilder = new CommonHouseBuilder();
        // 通过指挥者构建具体的房屋
        Director director = new Director(commonHouseBuilder, 5, 12, 2);
        House house = director.constructHouse();
        System.out.println(house);
    }
}
