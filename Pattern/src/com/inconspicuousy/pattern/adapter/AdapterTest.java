package com.inconspicuousy.pattern.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 5V电压充电器规范
interface IVoltage5V {
    int output5V();
}

// 手机
class Phone {
    // 给手机充电, 手机必须是5V的电压才能充电
    public void charging(IVoltage5V iVoltage5V) {
        // 利用5V充电器给手机充电
        int i = iVoltage5V.output5V();
        System.out.println("i = " + i);
        System.out.println("手机正在充电");
    }
}
// 家庭用电是220V
class Voltege220V {
    // 输出家庭用电是220V
    public int output() {
        return 220;
    }
}

// 电压适配器
// 类适配器 -》通过继承的方式， 对原始的类进行适配， 满足新的接口
// 类适配器不满足【合成复用原则】
class VoltageAdapter extends Voltege220V implements IVoltage5V {
    @Override
    public int output5V() {
        // 输出的是220V， 将其转换为5V
        int output = output();
        output /= 44;
        // 输出5V电压
        return output;
    }
}

@Getter
@Setter
@AllArgsConstructor
class VoltageAdapterType2 implements IVoltage5V {

    // 以组合的方式引入被适配的对象，这种方式为对象适配器
    private Voltege220V voltege220V;

    @Override
    public int output5V() {
        return voltege220V.output() / 44;
    }
}

/**
 * @author peng.yi
 */
public class AdapterTest {

    public static void main(String[] args) {
        // 类适配器
        VoltageAdapter voltageAdapter = new VoltageAdapter();
        Phone phone = new Phone();
        phone.charging(voltageAdapter);

        // 对象适配器
        VoltageAdapterType2 voltageAdapterType2 = new VoltageAdapterType2(new Voltege220V());
        phone.charging(voltageAdapterType2);
    }

}
