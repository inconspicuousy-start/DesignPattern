package com.inconspicuousy.pattern.proxy;

// cglib实现动态代理的原理 是代理类默认继承被代理类， 以子类的形式代理目标类 => cglib 代理也叫做子类代理

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

// 被代理类， 注意， 因为cglib代理是子类代理， 所以被代理类不能被final修饰，
// 同样，方法也不能被final修饰，因为涉及到方法重写，被final修饰后就不能实现重写了。 也就不会被拦截代理
// 方法也不能被static修饰， static修饰的方法与类相关， 无法被重写， 也就不能被拦截代理
class CglibDao {
    public void save() {
        System.out.println("目标对象执行保存操作");
    }

    public static void delete () {
        System.out.println("static - 目标对象执行静态函数操作");
    }
}

class CglibProxyFactory {

    // 定义被被代理的对象
    private final Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    // 就是利用newProxyInstance生成一个代理类， 利用Callback定义代理方法执行的逻辑
    public Object newProxyInstance() {
        // 创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 设置要继承的父类
        enhancer.setSuperclass(target.getClass());
        // 设置代理方法执行的逻辑
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("method = " + method);
                System.out.println("Log.....method before");
                Object invoke = method.invoke(target, objects);
                System.out.println("Log.....method after");
                return invoke;
            }
        });
        return enhancer.create();
    }
}

/**
 * cglib实现动态代理代码示例
 * @author peng.yi
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        CglibDao cglibDao = new CglibDao();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(cglibDao);
        // 获取到代理类对象
        CglibDao instance = (CglibDao) cglibProxyFactory.newProxyInstance();
        System.out.println("instance = " + instance);
        // 从打印信息来看获取到的是被代理类对象的CGlib增强类对象
        System.out.println("instance.getClass() = " + instance.getClass());
        // 它的父类就是被代理类
        System.out.println("instance.getClass().getSuperclass() = " + instance.getClass().getSuperclass());
        // 父类接口就是cglib包下的Factory对象
        System.out.println("instance.getClass().getInterfaces() = " + Arrays.toString(instance.getClass().getInterfaces()));
        instance.save();
        // 注意静态方法不会被拦截，与类相关包
        instance.delete();
    }
}
