package com.inconspicuousy.pattern.proxy;

// 代理模式：为一个对象提供一个替身，以控制对这个对象的访问，即客户端通过代理对象去访问目标对象。
// 代理对象类一般是与目标对象类形成组合关系， 代理对象底层都是调用目标对象， 但是调用目标对象之前或者之后可以添加自己的逻辑。
// 注意， 代理对象本质上只是为目标对象提供一个替身，添加其他功能只是附属品。
// 静态代理： 代理对象和目标对象都同时实现同一个接口， 通过代理类以组合的方式将目标对象依赖进来即可。
// 静态代理缺点: 因为代理类和被代理类都实现了同一个接口, 当接口添加一个方法时, 需要同时维护代理类和被代理类

// 公共接口
interface IDao {
    void save();
}

// 被代理类
class Dao implements IDao {
    @Override
    public void save() {
        System.out.println("目标对象执行保存操作");
    }
}

// 代理类
class StaticProxy implements IDao {

    private final Dao dao;
    public StaticProxy(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void save() {
        // 代理类核心还是执行的被代理类方法
        // 添加一些附属功能
        System.out.println("Log.......save before");
        dao.save();
        System.out.println("Log.......save after");
    }
}


/**
 * 代理模式-静态代理测试
 * @author peng.yi
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Dao dao = new Dao();
        StaticProxy staticProxy = new StaticProxy(dao);
        // 客户端只需要访问代理对象就可以间接访问目标对象
        staticProxy.save();
    }
}
