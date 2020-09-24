package com.inconspicuousy.pattern.proxy;

// JDK动态代理
// 动态代理核心就是在代码运行期间实现代理的功能。动态代理对象可以是任意实现了某个接口的类, 扩展性强.
// 动态代理实现的核心就是与静态代理类似, 只是在代码运行过程中, 创建一个与目标对象实现了同一个接口的代理对象(内存中创建代理对象)
// 注意， JDK动态代理对象必须实现一个接口。

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

// 抽象接口
interface DynamicIDao {
    void save();
}

// 被代理对象-目标对象
class DynamicDao implements DynamicIDao {
    @Override
    public void save() {
        System.out.println("目标对象执行保存操作");
    }
}

// 动态代理类
class DynamicProxyFactory{
    // 被代理对象-目标对象， 可以是任意实现了某个接口的对象
    private final Object object;
    public DynamicProxyFactory(Object object) {
        this.object = object;
    }

    // 提供一个获取到代理对象的方法
    public Object newProxyInstance() {
        // 其实核心就是利用反射代理类Proxy来创建对象
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            // proxy为代理对象
            // method为执行的方法
            // args为方法参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 最后一个参数为定义调用函数处理器
                // 注意，此处并不能打印代理对象， 因为会调用toString方法， 然后形成嵌套，抛出 StackOverflowError 异常
                // System.out.println("proxy = " + proxy);
                System.out.println("method = " + method);
                System.out.println("args = " + Arrays.toString(args));
                System.out.println("Log...........save before");
                // 此时我们可以直接执行方法， 但是执行方法的对象我们直接设为被代理对象
                Object invoke = method.invoke(object, args);
                System.out.println("Log...........save after");
                // 此时将被代理对象的执行结果返回
                return invoke;
            }
        });
    }
}

/**
 * 代理模式-动态代理
 * @author peng.yi
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        DynamicDao dynamicDao = new DynamicDao();
        DynamicProxyFactory dynamicProxyFactory = new DynamicProxyFactory(dynamicDao);
        // 强转成目标对象的接口类
        DynamicIDao o = (DynamicIDao) dynamicProxyFactory.newProxyInstance();
        System.out.println("o = " + o.getClass());
        // JDK动态代理默认也会实现代理类对象的父类接口
        System.out.println(Arrays.toString(o.getClass().getInterfaces()));
        // 代理类默认是继承Proxy类的
        System.out.println(o.getClass().getSuperclass());
        // 指定代理方法
        o.save();
    }
}
