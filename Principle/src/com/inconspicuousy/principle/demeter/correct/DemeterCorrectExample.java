package com.inconspicuousy.principle.demeter.correct;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** 学校总部员工类 */
@Getter
@Setter
@RequiredArgsConstructor
class Employee {
    /** 学校总部员工ID */
    @NonNull
    private String id;
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

    /** 打印所有的学院员工信息, 将学院员工信息在学院管理类打印, 避免学校管理类与之发生耦合 */
    public void printAllEmployee() {
        findAllEmployee().forEach(collegeEmployee -> System.out.println(collegeEmployee.getId()));
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
        collegeManager.printAllEmployee();
        System.out.println("==============学校总部员工==============");
        findAllEmployee().forEach(employee -> System.out.println(employee.getId()));
    }
}

/**
 * 迪米特法则正确示例
 *
 * @author peng.yi
 */
public class DemeterCorrectExample {

    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }
}
