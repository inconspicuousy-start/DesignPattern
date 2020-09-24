package com.inconspicuousy.pattern.facade;

// 外观模式就是提供一个外部接口来控制一组子系统
// 对于客户端来说只需要知道外部接口， 内部子系统的执行流程并不知道。
// 我们通过遥控（客户端）可以控制电视的开关（电视对接遥控器的开关命令就是外观模式的接口），
// 通过开，电视需要开启显示器，启动相应的软件，连接对应的WIFI，初始化显示界面等。

// 显示器
class Display {
    // 注意，该类都是单例的
    private static final Display DISPLAY = new Display();
    private Display() {
    }
    public static Display getDisplay() {
        return DISPLAY;
    }

    public void open() {
        System.out.println("打开显示器");
    }

    public void close() {
        System.out.println("关闭显示器");
    }
}

// 软件系统
class SoftSystem {
    // 注意，该类都是单例的
    private static final SoftSystem SOFT_SYSTEM = new SoftSystem();
    private SoftSystem() {
    }
    public static SoftSystem getSoftSystem() {
        return SOFT_SYSTEM;
    }
    public void initSoft() {
        System.out.println("初始化系统启动软件");
    }
    public void closeAllSoft() {
        System.out.println("关掉所有的软件");
    }
}

// 电视机
class Tv {
    private final SoftSystem softSystem;
    private final Display display;

    public Tv() {
        this.softSystem = SoftSystem.getSoftSystem();
        this.display = Display.getDisplay();
    }

    // 打开电视
    public void open() {
        // 按照一定的顺序开启子系统
        display.open();
        softSystem.initSoft();
    }

    // 关闭电视
    public void close() {
        // 按照一定的顺序关闭子系统
        softSystem.closeAllSoft();
        display.close();
    }
}

/**
 * 外观模式- 遥控器控制电视机示例代码
 * @author peng.yi
 */
public class facadeTest {
    public static void main(String[] args) {
    //    模拟遥控器控制电视机
        Tv tv = new Tv();
        System.out.println("===========打开电视");
        tv.open();
        System.out.println("===========关闭电视");
        tv.close();
    }
}
