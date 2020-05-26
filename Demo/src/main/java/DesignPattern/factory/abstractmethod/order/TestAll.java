package DesignPattern.factory.abstractmethod.order;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author maxin
 * @Date 2019-12-02 16:01
 * @ClassName TestAll
 * @Description
 * @Version 1.0
 **/
public class TestAll {
        private String name;
        private String dept;
        private int salary;
        private List<TestAll> subordinates;
        //构造函数
        public TestAll(String name, String dept, int sal) {
            this.name = name;
            this.dept = dept;
            this.salary = sal;
            subordinates = new ArrayList<TestAll>();
        }

        public void add(TestAll e) {
            subordinates.add(e);
        }

        public void remove(TestAll e) {
            subordinates.remove(e);
        }

        public List<TestAll> getSubordinates(){
            return subordinates;
        }

        public String toString(){
            return ("Employee :[ Name : "+ name
                    +", dept : "+ dept + ", salary :"
                    + salary+" ]");
        }
    }

class CompositePatternDemo {
    public static void main(String[] args) {
        TestAll CEO = new TestAll("John","CEO", 30000);

        TestAll headSales = new TestAll("Robert","Head Sales", 20000);

        TestAll headMarketing = new TestAll("Michel","Head Marketing", 20000);

        TestAll clerk1 = new TestAll("Laura","Marketing", 10000);
//        TestAll clerk2 = new TestAll("Bob","Marketing", 10000);

        TestAll headSalesadd = new TestAll("Richard","Sales", 10000);
//        TestAll salesExecutive2 = new TestAll("Rob","Sales", 10000);

        CEO.add(headSalesadd);
        CEO.add(headMarketing);

        headSales.add(headSalesadd);
//        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
//        headMarketing.add(clerk2);

        //打印该组织的所有员工
        System.out.println("CEO===="+CEO);
        for (TestAll headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (TestAll employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}