package com.mx.example.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * TODO:
 * 单链表的反转
 *
 * @author dangzw
 * @version 1.0, 2019/2/24/14:59
 */
public class LinkedListReverse {

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pcur = head;
        ListNode reNode = null;
        while (pcur != null) {
            ListNode nextNode = pcur.next;
            pcur.next = reNode;
            reNode = pcur;
            pcur = nextNode;
        }
        return reNode;
    }

    public static ListNode reverseListFor(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //当前节点
        ListNode pcur = head;
        //当前节点的后继节点
        ListNode temp = null;
        //当前节点的前驱节点
        ListNode prev = null;
        //反转后链表的头节点
        ListNode reNode = null;
        while (pcur != null) {
            reNode = pcur;
            //用来存当前节点的后继节点
            temp = pcur.next;
            //当前节点的前驱节点
            pcur.next = prev;
            //更新前驱节点
            prev = pcur;
            //更新后继节点
            pcur = temp;
        }
        return reNode;
    }

    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy.next;
        ListNode pCur = prev.next;
        while (pCur != null) {
            prev.next = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = prev.next;
        }

        return dummy.next;
    }

    //递归实现
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return reHead;
    }
    public static int strHashCode(String str){
        char[] val = str.toCharArray();
        int hash =0;
        if(hash==0&&val.length>0){
            for(int i=0;i<val.length;i++){
                hash = hash*31+val[i];
            }
        }
        return  hash;
    }

    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        List list = new ArrayList();
        String str = "aaa";
        str.hashCode();
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        //fourth.next = second;
        ListNode head = first;
        printListNode(head);
        head = first;
//        boolean isExitLoop = exitLoop(head);
//        System.out.println();
//        System.out.println(isExitLoop);
        head = insertNode(head, 5);
        head = insertNode(head,0);
        printListNode(head);
        System.out.println('\n'+"+++++++++++++++++++");
        head = sortListNode(head);
        printListNode(head);
//        head = deleteListNode(head, 2);
//        System.out.println('\n' + "+++++++++++++++++++");
//        printListNode(head);
    }

    public static void printListNode(ListNode head) {
        System.out.println();
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    //删除节点
    public static ListNode deleteListNode(ListNode head, int data) {
        if (head == null || (head.next == null && head.value != data)) {
            return head;
        }
        ListNode prev = null;
        ListNode pcur = head;
        while (pcur != null) {
            ListNode temp = null;
            if (pcur.value == data) {
                temp = pcur;
            } else {
                prev = pcur;
                pcur = pcur.next;
            }
            if (temp != null) {
                if (prev != null) {
                    prev.next = temp.next;
                } else {
                    head = temp.next;
                }
                return head;
            }
        }
        return head;
    }

    //插入节点
    public static ListNode insertNode(ListNode head, int data) {
        if (head == null) {
            head = new ListNode(data);
            head.next = null;
            return head;
        }
        if (head.next == null) {
            head.next = new ListNode(data);
            head.next.next = null;
            return head;
        }
        ListNode pcur = new ListNode(data);
        ListNode temp = head.next;
        pcur.next = head;
        pcur.next.next = temp;
        pcur = sortListNode(pcur);
        return pcur;
    }

    //链表节点排序
    public static ListNode sortListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode firstNode  = head;
        ListNode secondNode = head;
        for(firstNode=head;firstNode.next!=null;firstNode=firstNode.next){
            for(secondNode=head;secondNode.next!=null;secondNode=secondNode.next){
                if(secondNode.value>secondNode.next.value){
                    int temp = secondNode.value;
                    secondNode.value = secondNode.next.value;
                    secondNode.next.value = temp;
                }
            }
        }
        return head;
    }
//快排序未实现
//    public static void quickSort(ListNode head, ListNode end) {
//        ListNode pivot;
//        if (head != end) {
//            pivot = Patition(head,end);
//            quickSort(head,pivot);
//            quickSort(pivot.next,end);
//        }
//    }

//    public static ListNode Patition(ListNode head, ListNode end) {
//        if(head==null||head.next==null){
//            return head;
//        }
//        ListNode pivot_item = head;
//        ListNode pcur = head;
//        ListNode p
//        while(head.value<end.value){
//            if(head.value<=pivot_item.value){
//                head=head.next;
//            }
//
//
//        }
//        return null;
//    }

    public static ListNode getLastNode(ListNode head) {
        ListNode lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        return lastNode;
    }

    //判断链表是否有环
    public static boolean exitLoop(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                System.out.println(slow.value);
                return true;
            }
        }
        return false;
    }

    static class ListNode {
        private int value;
        private ListNode next;

        private ListNode(int value) {
            this.value = value;
            next = null;
        }
    }
}
