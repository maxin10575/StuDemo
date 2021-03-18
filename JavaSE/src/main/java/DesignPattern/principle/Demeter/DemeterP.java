package DesignPattern.principle.Demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author maxin
 * @Date 2019-11-15 17:45
 * @ClassName DemeterP  迪米特法则（又叫 最少知道原则）
 * @Description 1。一个对象应该对其他对象保持最少的了解
 * 2. 对于被依赖的类不管多么复杂，都尽量将逻辑封装在类的内部，对外除了提供public方法，
 * 不对外泄露任何信息
 * 3.直接朋友：成员变量，方法参数，方法返回值
 * 4.局部变量不是直接朋友
 * @Version 1.0
 **/
public class DemeterP {
    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }

}

class Employee {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class CollegeEmployee {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class CollegeManager {
    public List<CollegeEmployee> getAllEmployee() {
        List<CollegeEmployee> list = new ArrayList<CollegeEmployee>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployee emp = new CollegeEmployee();
            emp.setId("学院员工id= " + i);
            list.add(emp);
        }
        return list;
    }
}

//分析 直接朋友类：Employee  CollegeManager
//     CollegeEmployee不是直接朋友，是以局部变量方式出现。违背了迪米特法则
class SchoolManager {
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Employee employee = new Employee();
            employee.setId("学校总部员工id= " + i);
            list.add(employee);
        }
        return list;
    }

    void printAllEmployee(CollegeManager sub) {
        //局部变量
        List<CollegeEmployee> list3 = sub.getAllEmployee();
        System.out.println("======学院员工=====");
        for (CollegeEmployee e : list3) {
            System.out.println(e.getId());
        }
        List<Employee> list2 = this.getAllEmployee();
        System.out.println("=====学校总部员工=====");
        for (Employee e : list2) {
            System.out.println(e.getId());
        }
    }

}










