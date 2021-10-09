package ConcurrencyTest.creat;

import java.util.*;

/**
 * Collections 是集合的工具类 提供了一组方法，可以将现有的集合或Map转换为 线程安全的。
 * ArrayList，LinkedList，HashSet，HashMap都不是 线程安全的。
 *
 *  * 遍历集合通常会使用迭代器，但是要注意： ＡＰＩ文档中也描述了这一点。  。所以需要执行维护。
 *  * 迭代器有一个特点，就是在遍历集合的过程中 不能通过集合的方法修改元素数量，哪怕 该集合是一个线程安全的集合，在多线程
 *  * 情况下，迭代器遍历与集合元素的增删之间 也不存在互斥性，若不维护，会导致遍历 处现异常。
 *
 * @author maxin
 *
 */
public class CoverToSyncDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		System.out.println(list);

		Map<String, Integer> map = new HashMap<>();
		map.put("ss", 12);
		map.put("dd", 45);
		map.put("zz", 99);
		System.out.println(map);


		map = Collections.synchronizedMap(map);
		System.out.println(map);
		/*
		 * 将给定的集合转换为一个线程安全的集合
		 */
		list = Collections.synchronizedList(list);
		System.out.println(list);

		Set<String> set = new HashSet<>(list);
		set = Collections.synchronizedSet(set);
		System.out.println(set);

	}
}

