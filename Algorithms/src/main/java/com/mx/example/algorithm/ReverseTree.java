package com.mx.example.algorithm;

import sun.misc.Queue;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO:树的反转
 *
 * @author mx
 * @version 1.0, 2018/10/19/10:49
 */
public class ReverseTree {
    //二叉树节点类
    public static class TreeNode {
        int val = 0;
        TreeNode lefNode = null;//左孩子
        TreeNode rightNode = null;//右孩子

        public TreeNode(int val) {
            this.val = val;
        }

    }

    public static class Solution {
        public static List<TreeNode> nodeList = null;

        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            createTree(arr);
            try {
                bfsTree(nodeList.get(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("aaaaaaaaaaaaaaaaaa");
            //levelTraverse(nodeList.get(0));
            TreeNode newRoot = invertTree(nodeList.get(0));
            try {
                bfsTree(nodeList.get(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            levelTraverse(newRoot);
        }

        //反转二叉树
        private static TreeNode invertTree(TreeNode treeNode) {
            if (treeNode == null) {
                return null;
            }
            TreeNode tmp = treeNode.lefNode;
            treeNode.lefNode = treeNode.rightNode;
            treeNode.rightNode = tmp;
            invertTree(treeNode.lefNode);
            invertTree(treeNode.rightNode);
            return treeNode;

        }

        //遍历二叉树
        private static void levelTraverse(TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }
            LinkedList<TreeNode> list = new LinkedList<>();
            list.add(treeNode);
            while (list.size() != 0) {
                TreeNode node = list.removeFirst();
                System.out.print(node.val + " ");
                if (node.lefNode != null) {
                    list.add(node.lefNode);
                }
                if (node.rightNode != null) {
                    list.add(node.rightNode);
                }
            }
            System.out.println();

        }
        //遍历二叉树，广度优先算法
        public static void bfsTree(TreeNode treeNode) throws InterruptedException {
            if(treeNode==null){
                return;
            }
            Queue<TreeNode> queue = new Queue<>();
            queue.enqueue(treeNode);
            TreeNode temp =null;
            while (!queue.isEmpty()){
                temp = queue.dequeue();
                System.out.print(temp.val+" ");
                if(temp.lefNode!=null){
                    queue.enqueue(temp.lefNode);
                }
                if(temp.rightNode!=null){
                    queue.enqueue(temp.rightNode);
                }

            }
            System.out.println();

        }

        //创建二叉树
        private static void createTree(int[] arr) {
            nodeList = new LinkedList<>();
            for (int i = 0; i < arr.length; i++) {
                nodeList.add(new TreeNode(arr[i]));
            }
            //最后一个父亲结点在数组中的位置
            int lastParentIndex = arr.length / 2 - 1;
            for (int parentIndex = 0; parentIndex < lastParentIndex; parentIndex++) {
                nodeList.get(parentIndex).lefNode = nodeList.get(parentIndex * 2 + 1);
                nodeList.get(parentIndex).rightNode = nodeList.get(parentIndex * 2 + 2);
            }
            //最后一个父节点：因为最后一个父亲结点可能没有右孩子，所以单独拿出来处理
            nodeList.get(lastParentIndex).lefNode = nodeList.get(lastParentIndex * 2 + 1);
            if (arr.length % 2 == 1) {
                nodeList.get(lastParentIndex).rightNode = nodeList.get(lastParentIndex * 2 + 2);
            }
        }

    }
}
