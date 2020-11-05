package com.mx;


import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MergeTwoSortedLists {

	@Test
	public void testsort(){
		HashSet<Integer> menuidset = new HashSet<>();
//		Set<testEntity> menuidset=new TreeSet<>();
		menuidset.add(2);
		menuidset.add(8);
		menuidset.add(-1);
		menuidset.add(4);
		menuidset.add(2);
	/*	Set<Integer> sortSet = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);//降序排列
			}
		});
		sortSet.addAll(menuidset);*/
		Set<Integer> sortSet = new TreeSet<>(Comparator.naturalOrder());
		sortSet.addAll(menuidset);
		System.out.println(sortSet.toString());

	/*	Set<Integer> sortSet = new TreeSet<Integer>(Comparator.reverseOrder());
		sortSet.addAll(menuidset);*/
	}
	
	
	@Test
	public void test(){
		ListNode l1 = new ListNode();
		l1.add(1);
		l1.add(2);
		l1.add(4);
		ListNode l2 = new ListNode();
		l2.add(1);
		l2.add(3);
		l2.add(4);
		ListNode  listnode = mergeTwoLists(l1,l2);
		listnode.print();

	}
	
	 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        if (l1 == null) { 
	            return l2;
	        }
	        else if (l2 == null) {
	            return l1;
	        }
	        else if (l1.val < l2.val) {
	            l1.next = mergeTwoLists(l1.next, l2);
	            return l1;
	        }
	        else {
	            l2.next = mergeTwoLists(l1, l2.next);
	            return l2;
	        }
	    }
	 
}
