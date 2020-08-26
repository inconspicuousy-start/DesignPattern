package com.inconspicuousy.principle.demeter.error;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** 学校总部员工类 */
@Getter
@Setter
@RequiredArgsConstructor
class Employee {
    /** 学校总部员工ID */
    @NonNull private String id;
}

/** 学院员工类 */
@Getter
@Setter
@RequiredArgsConstructor
class CollegeEmployee {
    /** 学院员工ID */
    @NonNull private String id;
}

/** 学院管理类 */
class CollegeManager {
    /** 返回学院所有的员工 */
    public List<CollegeEmployee> findAllEmployee() {
        // 添加10个员工到列表
        return IntStream.rangeClosed(1, 10).boxed()
                .map(i -> new CollegeEmployee("学院ID" + i))
                .collect(Collectors.toList());
    }
}
/** 学校总部管理类 */
class SchoolManager {
    /** 返回学校总部所有的员工 */
    public List<Employee> findAllEmployee() {
        // 添加5个员工到列表
        return IntStream.rangeClosed(1, 5).boxed()
                .map(i -> new Employee("学校总部ID" + i))
                .collect(Collectors.toList());
    }

    /** 打印出所有的员工信息 */
    public void printAllEmployee(CollegeManager collegeManager) {
        System.out.println("=================学院员工=====================");
        // 这里有一个问题就是, 按照案例描述, 我们在设计时, 学校总部应该不需要牵涉到学院的员工, 也就是
        // SchoolManager不应该与CollegeEmployee产生耦合关系
        // 这里在遍历过程中实质上引入了CollegeEmployee局部变量, 出现了非直接朋友关系类, 不符合迪米特法则,
        collegeManager.findAllEmployee().forEach(collegeEmployee -> System.out.println(collegeEmployee.getId()));
        System.out.println("==============学校总部员工==============");
        findAllEmployee().forEach(employee -> System.out.println(employee.getId()));
    }
}

/**
 * 迪米特法则错误案例
 *
 * <p>应用案例: 有一个学校,下属有各个学院和总部, 现要求打印出学校总部员工ID和学院员工的ID</p>
 *
 *  @author peng.yi
 */
public class DemeterErrorExample {
    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }
}
