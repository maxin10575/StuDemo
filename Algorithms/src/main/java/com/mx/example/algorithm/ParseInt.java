package com.mx.example.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO: 实现Integer的parseInt方法
 *
 * @author dangzw
 * @version 1.0, 2019/1/15/15:55
 */
public class ParseInt {
    public static void main(String[] args) {
        String str = "-2147483649";
        try {
            //System.out.println(Integer.parseInt(str));
            int maxNum = (int) ((Math.pow(2, 31))-1);
            System.out.println(parseStrToInts(str));
            List<String> list =new ArrayList<>();
            List<String> list2 = new LinkedList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int parserInt(String str) throws Exception {
        return parserInt(str, 10);
    }

    //jdk实现
    private static int parserInt(String str, int radix) throws Exception {
        if (str == null) {
            throw new NumberFormatException("null");
        }
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix
                    + "less than Character.MIN_RADIX");
        }
        if (radix > Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    "greater than Character.MAX_RADIX");
        }
        int result = 0;
        boolean negative = false;
        int i = 0, max = str.length();
        int limit;
        int multmin;
        int digit;
        if (max > 0) {
            if (str.charAt(0) == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
                i++;
            } else {
                limit = -Integer.MAX_VALUE;
            }
            multmin = limit / radix;
            if (i < max) {
                digit = Character.digit(str.charAt(i++), radix);
                if (digit < 0) {
                    throw new Exception(str);
                } else {
                    result = -digit;
                }
            }
            while (i < max) {
                digit = Character.digit(str.charAt(i++), radix);
                if (digit < 0) {
                    throw new Exception(str);
                }
                if (result < multmin) {
                   throw new Exception(str);
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new Exception(str);
                }
                result = -digit;
            }

        } else {
            throw new Exception(str);
        }
        if (negative) {
            if (i > 1) {
                return result;
            } else {
                throw new Exception(str);
            }
        } else {
            return -result;
        }
    }

    public static int parseStrToInt(String str) throws Exception {
        int i = 0, max, len;
        int maxNum = (int) ((Math.pow(2, 31))-1);
        len = max = str.length();
        int result = 0;
        int j = 0;
        boolean isNegative = false;
        if (max == 0 || max > 32) {
            throw new Exception("字符串格式异常");
        }
        if (max > 0) {
            if (str.charAt(0) == '-') {
                isNegative = true;
                i++;
                j = 1;
                len = max - 1;
            } else {
                j = 0;
            }
            while (j == 0 && i < max) {
                if (str.charAt(i) == '-') {
                    throw new Exception("字符串格式异常");
                }
                i++;
            }
            while (j < max) {
                result += (str.charAt(j) - 48) * Math.pow(10, len - 1);
                if(result>maxNum){
                   throw new Exception("字符串长度超出了int数据类型取值范围");
                }
                j++;
                len--;
            }
        }
        if (isNegative) {
            result = -result;
        }
        return result;
    }
    public static Integer parseStrToInts(String str){
        if(str==null){
            return null;
        }
        boolean flag = (str.charAt(0)=='-');
        int index = flag?1:0;
        int digit = Character.digit(str.charAt(index++),10);
        int temp = -digit;
        while (index<str.length()){
            digit = Character.digit(str.charAt(index++),10);
            temp *=10;
            temp -=digit;
        }
        if(flag){
            return temp;
        }else{
            return -temp;
        }
    }
    public static int StrToInt(String str) {
        int len = str.length();
        if(len == 0) return 0;
        int index = 0;
        boolean minus = false;
        if (str.charAt(0) == '+') {
            index++;
        } else if(str.charAt(0) == '-') {
            minus = true;
            index++;
        }
        if(index != 0 && len == 1) {//万一只有一个正负号符号，而没有数字的情况要格外注意啊 ！！！
            return 0;
        }
        int num = StrToInt(str, index, minus);
        if(num != 0) num = minus ? num*(-1) : num;
        return  num;
    }

    public static int StrToInt(String str,int index,boolean minus) {
        int result = 0;
        int len = str.length();
        for(int i = index; i < len; i++) {
            if(str.charAt(i) >= '0' && str.charAt(i)<= '9') {//各个位置上的字符是否合格
                result = result*10;
                result += str.charAt(i)-'0';//累加的过程，从高位到低位
                if((minus && (result*-1) > 0) || (!minus && (result < 0))) {//溢出时就是原来是正数，加着加着变成了负数，或者原来是负数加着加着变成了正数
                    result = 0;
                    break;
                }
            } else {
                result = 0;
                break;
            }
        }
        return result;
    }

}
