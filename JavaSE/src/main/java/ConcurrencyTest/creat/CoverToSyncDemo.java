package ConcurrencyTest.creat;

import java.util.*;

/**
 * Collections �Ǽ��ϵĹ����� �ṩ��һ�鷽�������Խ����еļ��ϻ�Mapת��Ϊ �̰߳�ȫ�ġ�
 * ArrayList��LinkedList��HashSet��HashMap������ �̰߳�ȫ�ġ�
 *
 *  * ��������ͨ����ʹ�õ�����������Ҫע�⣺ ���У��ĵ���Ҳ��������һ�㡣  ��������Ҫִ��ά����
 *  * ��������һ���ص㣬�����ڱ������ϵĹ����� ����ͨ�����ϵķ����޸�Ԫ������������ �ü�����һ���̰߳�ȫ�ļ��ϣ��ڶ��߳�
 *  * ����£������������뼯��Ԫ�ص���ɾ֮�� Ҳ�����ڻ����ԣ�����ά�����ᵼ�±��� �����쳣��
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
		 * �������ļ���ת��Ϊһ���̰߳�ȫ�ļ���
		 */
		list = Collections.synchronizedList(list);
		System.out.println(list);

		Set<String> set = new HashSet<>(list);
		set = Collections.synchronizedSet(set);
		System.out.println(set);

	}
}

