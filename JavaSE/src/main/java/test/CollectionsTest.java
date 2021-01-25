package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-06-02 15:54
 * @Modified By:
 * @Version: 1.0
 **/
public class CollectionsTest {
    public static void main(String[] args) {
        List<PersonData> list = new ArrayList<PersonData>();

        PersonData p0 = new PersonData();
        p0.setId("0");
        p0.setName("小明");
        p0.setType("管理员");
        p0.setAge(20);
        list.add(p0);

        PersonData p1 = new PersonData();
        p1.setId("1");
        p1.setName("张三");
        p1.setType("管理员");
        p1.setAge(40);
        list.add(p1);

        PersonData p2 = new PersonData();
        p2.setId("2");
        p2.setName("李四");
        p2.setType("管理员");
        p2.setAge(30);
        list.add(p2);

        PersonData p3 = new PersonData();
        p3.setId("1");
        p3.setName("王五");
        p3.setType("用户");
        p3.setAge(40);
        list.add(p3);

//按照多个属性分组，key为多个属性合
//        Map<String,List<E>> result=list.stream.collect(Collectors.groupingBy(e -> e.getName() + e.getAge()));
//按照多个属性分组，key为单个属性合
//        Map<String,Map<String,List<Student>>> result=
//                list.stream.collect(Collectors.groupingBy(Student::getName, Collectors.groupingBy(Student::getAge)));

        Object collect1 =
//        Map<String, List<PersonData>> collect1 =
                list.stream().collect(Collectors.groupingBy(PersonData::getId,
                        Collectors.groupingBy(PersonData::getAge)));

        Iterator<Map.Entry<String, List<PersonData>>> entries = ((Map<String, List<PersonData>>) collect1).entrySet().iterator();

        System.out.println(collect1);



/*    ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(-1);
		arrayList.add(3);
		arrayList.add(3);
		arrayList.add(-5);
		arrayList.add(7);
		arrayList.add(4);
		arrayList.add(-9);
		arrayList.add(-7);
		System.out.println("原始数组:");
		System.out.println(arrayList);
    // void reverse(List list)：反转
		Collections.reverse(arrayList);
		System.out.println("Collections.reverse(arrayList):");
		System.out.println(arrayList);

////旋转。当distance为正数时，将list后distance个元素整体移到前面。当distance为负数时，将 list的前distance个元素整体移到后面。
		Collections.rotate(arrayList, 4);
		System.out.println("Collections.rotate(arrayList, 4):");
		System.out.println(arrayList);

    // void sort(List list),按自然排序的升序排序
		Collections.sort(arrayList);
		System.out.println("Collections.sort(arrayList):");
		System.out.println(arrayList);

    // void shuffle(List list),随机排序
		Collections.shuffle(arrayList);
		System.out.println("Collections.shuffle(arrayList):");
		System.out.println(arrayList);

    // void swap(List list, int i , int j),交换两个索引位置的元素
		Collections.swap(arrayList, 2, 5);
		System.out.println("Collections.swap(arrayList, 2, 5):");
		System.out.println(arrayList);
    // 定制排序的用法
		Collections.sort(arrayList, new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });
		System.out.println("定制排序后：");
		System.out.println(arrayList);*/
/*
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        arrayList2.add(-3);
        arrayList2.add(-5);
        arrayList2.add(7);
//        arrayList2.add(-7);
        System.out.println("原始数组:");
        System.out.println(arrayList);

        System.out.println("Collections.max(arrayList):");
        System.out.println(Collections.max(arrayList));

        System.out.println("Collections.min(arrayList):");
        System.out.println(Collections.min(arrayList));

        System.out.println("Collections.replaceAll(arrayList, 3, -3):");
        Collections.replaceAll(arrayList, 3, -3);
        System.out.println(arrayList);
        //统计元素出现次数
        System.out.println("Collections.frequency(arrayList, -3):");
        System.out.println(Collections.frequency(arrayList, -3));
        //统计target在list中第一次出现的索引，找不到则返回-1，类比int lastIndexOfSubList(List source, list target).
        System.out.println("Collections.indexOfSubList(arrayList, arrayList2):");
        System.out.println(Collections.indexOfSubList(arrayList, arrayList2));

        System.out.println("Collections.binarySearch(arrayList, 7):");
        // 对List进行二分查找，返回索引，List必须是有序的
        Collections.sort(arrayList);
        System.out.println("sort:"+arrayList);
        System.out.println(Collections.binarySearch(arrayList, 7));*/

   /*     Integer x = 3;
        Integer y = 3;
        System.out.println(x == y);// true
        Integer a = new Integer(129);
        Integer b = new Integer(129);
        System.out.println(a == b);//false
        System.out.println(a.equals(b));//true*/


    }
}

class PersonData {
    private String id;
    private String type;
    private String name;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonData(String id, String type, String name, int age) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public PersonData() {

    }
}
