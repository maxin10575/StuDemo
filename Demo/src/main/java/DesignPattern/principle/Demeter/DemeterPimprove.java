package DesignPattern.principle.Demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author maxin
 * @Date 2019-11-18 11:15
 * @ClassName DemeterPimprove
 * @Description TODO
 * @Version 1.0
 **/
public class DemeterPimprove {
}

class Employee1 {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

class CollegeEmployee1 {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

class CollegeManager1 {
    public List<CollegeEmployee1> getAllEmployee() {
        List<CollegeEmployee1> list = new ArrayList<CollegeEmployee1>();
        for(int i = 0; i<10; i++){
            CollegeEmployee1 emp = new CollegeEmployee1();
            emp.setId("学院员工id= "+i);
            list.add(emp);
        }
        return list;
    }
    //输出学院员工的信息
    public void printEmployee() {
        List<CollegeEmployee1> list1 = this.getAllEmployee();
        System.out.println("======学院员工=====");
        for (CollegeEmployee1 e : list1) {
            System.out.println(e.getId());
        }
    }
}

//分析 直接朋友类：Employee1  CollegeManager1
//     CollegeEmployee1不是直接朋友，是以局部变量方式出现。违背了迪米特法则
class SchoolManager1 {
    public List<Employee1> getAllEmployee() {
        List<Employee1> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Employee1 employee = new Employee1();
            employee.setId("学校总部员工id= " + i);
            list.add(employee);
        }
        return list;
    }

    void printAllEmployee(CollegeManager1 sub) {
        //改进后
        sub.printEmployee();
        //分析问题
        //1.将输出学院员工的方法，分装到CollegeManager

//        List<CollegeEmployee1> list1 = sub.getAllEmployee();
//        System.out.println("======学院员工=====");
//        for (CollegeEmployee1 e : list1) {
//            System.out.println(e.getId());
//        }
        List<Employee1> list2 = this.getAllEmployee();
        System.out.println("=====学校总部员工=====");
        for (Employee1 e : list2) {
            System.out.println(e.getId());
        }
    }
}