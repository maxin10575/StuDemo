package com.mx.example.algorithm;

import java.util.*;

/**
 * TODO:广度优先算法
 *
 * @author mx
 * @version 1.0, 2019/3/30/00:02
 */
public class BFSWork {
    //搜索方向
    static int[][] direct = {{0,1},{0,-1},{-1,0},{1,0}};
    //搜索标记
    static int[][] arc = new int[4][4];//辅助标记数组
    //输入数组
    static int[][] array={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

    public static void main(String[] args) {
        bfs();
    }
    //封装数组节点用坐标表示位置
    static class Node{
        int row;
        int column;
        int round;

        public Node(int row, int column, int round) {
            this.row = row;
            this.column = column;
            this.round = round;
        }
    }

    public static void bfs() {
        Node start = new Node(0,0,0);
        //待搜索队列
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(start);
        arc[0][0]=1;
        while(!queue.isEmpty()){
            //把入队的已访问节点出队
            Node temp = queue.poll();
            System.out.println("数值："+array[temp.row][temp.column]+", 轮次："+(temp.round+1));
            //尝试搜索四个方向的点，如果满足加入待搜索队列
            for(int i=0;i<4;i++){
                int new_row = temp.row+direct[i][0];
                int new_column = temp.column+direct[i][1];
                if(new_row<0||new_column<0||new_row>=4||new_column>=4){
                    continue;
                }
                if(arc[new_row][new_column]==1){
                    continue;
                }
                arc[new_row][new_column]=1;

                Node next = new Node(new_row,new_column,temp.round+1);
                queue.offer(next);
            }
        }
        System.out.println(queue.size());
    }
}
