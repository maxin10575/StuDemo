package test;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.jdbc.Null;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-06-02 11:00
 * @Modified By:
 * @Version: 1.0
 **/

@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Hashtest {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String sex;
  /*  public Hashtest(int id , String name){
        this.id = id;
        this.name = name;
    }*/

    public static void main(String[] args) {
//        Hashtest hashtest = new Hashtest();
//        hashtest.setId(1).setName("Zhangsan");
//        System.out.println("name：" + hashtest.getName() +"  id:"+ hashtest.getId());

//        @RequiredArgsConstructor(staticName = "of")
        Hashtest hashtest = Hashtest.of("name","id").setId(1).setName("ha").setSex("man");

//        Hashtest hashtest = Hashtest.of("Zhangsan").setId(1).setName(null);
        System.out.println(hashtest);

   /*     Hashtest stu1 = new Hashtest(1, "赤骥");
        Hashtest stu2 = new Hashtest(2, "赤骥");
        Hashtest stu3 = new Hashtest(3, "白义");*/
/*
        Set<Hashtest> students = new HashSet<>();
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        System.out.println(students.size());

        System.out.println("赤骥的HashCode：" + stu1.hashCode());
        System.out.println("赤骥的HashCode：" + stu2.hashCode());
        System.out.println("白义的HashCode：" + stu3.hashCode());
        System.out.println("equals：==" + stu2.equals(stu1));
        System.out.println("：==" + stu2.equals(stu1));
        System.out.println("object的toString：" + stu3.toString());*/
    }
}




