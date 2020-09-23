package com.inconspicuousy.pattern.bridge;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 将手机类型抽象化成抽象类
@Getter
@Setter
abstract class PhoneType {
    private String type;
    public PhoneType(String type) {
        this.type = type;
    }
}

// 折叠类型手机
@ToString
class FoldPhone extends PhoneType {
    public FoldPhone(String type) {
        super(type);
    }
}
// 全屏手机
@ToString
class TouchPhone extends PhoneType {
    public TouchPhone(String type) {
        super(type);
    }
}

// 手机品牌
@Getter
@Setter
abstract class BoardPhone {
    protected String name;
    // 以组合的形式将多维度特点结合起来形成一个新的产品
    private PhoneType phoneType;
    public BoardPhone(String name, PhoneType phoneType) {
        this.name = name;
        this.phoneType = phoneType;
    }
}

// 小米品牌手机
@ToString
class MiPhone extends BoardPhone {
    public MiPhone(String name, PhoneType phoneType) {
        super(name, phoneType);
    }
}

// 华为品牌手机
@ToString
class HWPhone extends BoardPhone {
    public HWPhone(String name, PhoneType phoneType) {
        super(name, phoneType);
    }
}

/**
 * 桥接模式-手机示例代码测试
 * @author peng.yi
 */
public class PhoneTest {
    public static void main(String[] args) {
        FoldPhone foldPhone = new FoldPhone("折叠手机");
        TouchPhone touchPhone = new TouchPhone("全屏手机");

        // 我们可以通过组合的形式创建各种 品牌和类型 的手机对象
        MiPhone miPhone = new MiPhone("小米", foldPhone);
        HWPhone hwPhone = new HWPhone("华为", foldPhone);
        // 当我们添加苹果品牌时， 只需要添加ApplePhone类继承对应的BoardPhone抽象类， 我们就可以实现对应的功能。
    }
}
