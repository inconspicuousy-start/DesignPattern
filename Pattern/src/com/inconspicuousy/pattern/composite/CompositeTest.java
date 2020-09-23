package com.inconspicuousy.pattern.composite;

// 情景描述： 打印出学校、学院、系的所有的信息
// 在当前情境中， 学校是根节点， 学院是子节点， 系是叶子节点

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
// 涉及到集合元素的话， 都需要重写equals方法
@EqualsAndHashCode
// 公用的组件抽象类， 对于客户端来说不管是叶子节点对象还是组合对象都是节点对象
// 所有的节点必须都继承该公用的组件抽象类
abstract class OrganizationComponent {
    private String name;

    // 所有的节点必须拥有名称用户打印
    public OrganizationComponent(String name) {
        this.name = name;
    }

    // 默认是不支持的， 因为叶子节点是不能添加元素的
    public void add(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    // 默认是不支持的， 因为叶子节点是不能删除元素的
    public void remove(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    // 核心打印方法，交给子类自己实现
    abstract public void print();
}

// 大学类
class University extends OrganizationComponent {

    // 初始化一个节点列表， 用于存储子节点对象
    private final List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name) {
        super(name);
    }

    @Override
    // 注意，在实际的场景中，大学和学院添加和删除操作不会一样
    public void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    public void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    @Override
    public void print() {
        System.out.println("===============name============= " + getName());
        organizationComponents.forEach(OrganizationComponent::print);
    }
}

// 学院节点
class College extends OrganizationComponent {
    // 初始化一个节点列表， 用于存储子节点对象
    private final List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name) {
        super(name);
    }

    @Override
    public void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    public void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    @Override
    public void print() {
        System.out.println("===============name============= " + getName());
        organizationComponents.forEach(OrganizationComponent::print);
    }
}

// 系叶子节点
class Department extends OrganizationComponent {
    public Department(String name) {
        super(name);
    }
    @Override
    public void print() {
        System.out.println("===============name============= " + getName());
    }
}


/**
 * 结构型模式-组合模式-打印学校组织结构示例代码
 * @author peng.yi
 */
public class CompositeTest {

    public static void main(String[] args) {
        // 定义大学
        University university = new University("湖北第二师范学院");

        // 定义学院
        College college = new College("计算机科学与技术学院");
        College college1 = new College("外国语学院");

        // 定义系
        Department department = new Department("软件工程");
        Department department1 = new Department("嵌入式工程");
        Department department2 = new Department("英语");

        university.add(college);
        university.add(college1);

        college.add(department);
        college.add(department1);

        college1.add(department2);

        university.print();
        System.out.println("====");
        college.print();
        System.out.println("====");
        department.print();
    }
}
