package com.mx;
/**
 * 
 * @author mx
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 */
public class reverseInterger {

	public static void main(String[] args) {
		 int num = 2147483641;
		 int num1= -2147483648;
		 int reversenum = reverse(num);
//		 int reversenum1 = reverse(num1);
		 System.out.println("===="+reversenum);
//		 System.out.println("====111"+reversenum1);

}


public static int reverse(int x) {
    int rev = 0;
    while (x != 0) {
        int pop = x % 10;
        x /= 10;
        if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {//7
        	return 0;
        }
        if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {//-8
        	return 0;
        }
        rev = rev * 10 + pop;
    }
    return rev;
}

}
