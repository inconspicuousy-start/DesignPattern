package com.inconspicuousy.pattern.templatemethod;

// 模板方法：在抽象类中定义一个算法的骨架，而将算法的一些步骤延迟到子类中，使得子类可以在不改变该算法结构的情况下，重新定义该算法的某些特定的步骤。
// 注意，抽象类的中的算法骨架方法一般用final修饰，不能被子类重写，子类只能对骨架中的一些步骤进行重写，实现某些特定的步骤。
// 在算法骨架中, 我们可以添加一些必要的钩子函数, 用来给子类在每个核心步骤定义一些自己的功能.

// 抽象父类， 定义核心算法骨架
abstract class SoyMildMachine {

    // 模版方法， 制作豆浆
    // 创建制作豆浆的骨架
    // 模版方法用final修饰， 防止子类重写算法
    public final void make() {
    //    定义算法骨架
        prepare();
        add();
        // 添加钩子函数
        if (ifAddComditions()) {
            addConditions();
        }
        churn();
        success();
    }

    // 准备材料
    public abstract void prepare();

    // 添加材料
    public void add() {
        System.out.println("添加材料");
    }

    // 钩子函数。决定是否添加辅助材料
    public boolean ifAddComditions() {
        return false;
    }

    // 默认是不支持的添加辅助材料的， 子类可以进行重写
    public void addConditions() {
        throw new UnsupportedOperationException();
    }

    // 搅拌
    public void churn() {
        System.out.println("搅拌");
    }

    // 完成
    public void success() {
        System.out.println("完成");
    }
}

// 红豆
class RedBeans extends SoyMildMachine {

    // 骨架中的一些步骤延迟到子类， 由子类自定义
    @Override
    public void prepare() {
        System.out.println("准备红豆");
    }

    // 对沟子函数进行重写
    @Override
    public boolean ifAddComditions() {
        return true;
    }

    // 添加一些牛奶
    @Override
    public void addConditions() {
        System.out.println("添加一些牛奶");
    }
}

/**
 *
 * 模板方法 - 榨豆浆代码示例
 * @author peng.yi
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
        RedBeans redBeans = new RedBeans();
        redBeans.make();
    }
}
