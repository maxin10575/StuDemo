package com.mx.example.algorithm;

/**
 * TODO:分治算法计算x的n次方
 *
 * @author dangzw
 * @version 1.0, 2019/1/11/10:40
 */
public class Divide {
    public static void main(String[] args) {
        int x = 2;
        int n = 10;
        System.out.println(mypow(x,n));
        System.out.println(mypower(x, n));
    }
    //分治算法
    private static int mypow(int x,int n) {
        if(n==0){
            return 1;
        }
        if(n<0){
            return 1/mypow(x,-n);
        }
        if(n%2==1){
            return x*mypow(x,n-1);
        }
        return mypow(x*x,n/2);
    }
    //非递归调用
    private static int mypower(int x,int y) {
        int pow=1;
        if (y < 0) {
            x = 1 / x;
            y = -y;
        }
        if (y == 0) {
            pow = 1;
            return pow;
        }
        while (y>0) {
            if (y % 2 == 1) {
                pow *= x;
            }
            x *= x;
            y >>= 1;
        }
        return pow;
    }
}
