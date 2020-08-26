package com.inconspicuousy.uml.dependency;

class A {}
class B extends A {}
class C {}
class D {}

class E {
    // 成员变量
    private A a;

    // 方法参数
    public E(A a) {
        this.a = a;
    }

    // 方法参数 + 返回值 + 局部变量
    public B method(C c) {
        D d = new D();
        return new B();
    }

}

/**
 * UML图中依赖关系
 * @author peng.yi
 */
public class DependencyExample {
}
