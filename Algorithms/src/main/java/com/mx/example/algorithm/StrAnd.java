package com.mx.example.algorithm;

/**
 * TODO:两个字符串大树相加
 *
 * @author mx
 * @version 1.0, 2019/2/21/9:47
 */
public class StrAnd {
    public static void main(String[] args) {
        String str1 = "123456";
        String str2 = "1111111";
        String andStr = lonStrAnd(str1, str2);
        String andStr2 = lonStrAnd2(str1, str2);
        System.out.println(andStr);
        System.out.println(andStr2);
    }

    public static String lonStrAnd2(String str1, String str2) {
        if (str1.length() == 0 || " ".equals(str1)) {
            return str2;
        }
        if (str2.length() == 0 || " ".equals(str2)) {
            return str1;
        }
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        StringBuffer sb = new StringBuffer(Math.max(c1.length, c2.length) + 1);
        int carry = 0;
        int currentNum = 0;
        int k, middle;
        if (c1.length >= c2.length) {
            k = c2.length - 1;
            middle = c1.length - c2.length - 1;
            for (int i = c1.length - 1; i > middle; i--) {
                currentNum = c1[i] + c2[k] - 2 * '0' + carry;
                carry = currentNum / 10;
                currentNum = currentNum % 10;
                sb.append(currentNum);
                k--;
            }
            for (int j = middle; j >= 0; j--) {
                currentNum = c1[j] - '0' + carry;
                carry = currentNum / 10;
                currentNum = currentNum % 10;
                sb.append(currentNum);
            }
            if (carry > 0) {
                sb.append(carry);
            }
        } else {
            k = c1.length - 1;
            middle = c2.length - c1.length - 1;
            for (int i = c2.length - 1; i > middle; i--) {
                currentNum = c2[i] + c1[k] - 2 * '0' + carry;
                carry = currentNum / 10;
                currentNum = currentNum % 10;
                sb.append(currentNum);
                k--;
            }
            for (int j = middle; j >= 0; j--) {
                currentNum = c2[j] - '0' + carry;
                carry = currentNum / 10;
                currentNum = currentNum % 10;
                sb.append(currentNum);
            }
            if (carry > 0) {
                sb.append(carry);
            }
        }
        return sb.reverse().toString();
    }

    public static char[] getCharAnd(char[] c1, char[] c2) {
        int carry = 0;
        int currentNum = 0;

        return null;
    }

    public static String lonStrAnd(String str1, String str2) {
        if (str1.length() == 0 || " ".equals(str1)) {
            return str2;
        }
        if (str2.length() == 0 || " ".equals(str2)) {
            return str1;
        }
        int maxLength = Math.max(str1.length(), str2.length());
        StringBuffer sb = new StringBuffer(maxLength + 1);
        str1 = new StringBuffer(str1).reverse().toString();
        str2 = new StringBuffer(str2).reverse().toString();
        //进位
        int carry = 0;
        //当前值
        int currentNum = 0;
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            currentNum = str1.charAt(i) + str2.charAt(i) - 2 * '0' + carry;
            carry = currentNum / 10;
            currentNum = currentNum % 10;
            sb.append(currentNum);
        }
        if (str1.length() < str2.length()) {
            str1 = str2;
        }
        for (int j = minLength; j < str1.length(); j++) {
            currentNum = str1.charAt(j) - '0' + carry;
            carry = currentNum / 10;
            currentNum = currentNum % 10;
            sb.append(currentNum);
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
