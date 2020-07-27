package algorithm;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonPrefix {
/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
说明:

所有输入只包含小写字母 a-z 。
 * @param args
 */
	public static void main(String[] args) {
		String []strarr = {"flower","flow","flight"};
		String result = LongestCommonPre2(strarr);
		System.out.println(result);
	

	}
	
	public static String LongestCommonPre2(String [] strs){
	
	   if (strs.length == 0) return "";
	   String prefix = strs[0];
	   for (int i = 1; i < strs.length; i++)
	       while (strs[i].indexOf(prefix) != 0) {
	           prefix = prefix.substring(0, prefix.length() - 1);
	           if (prefix.isEmpty()) return "";
	       }        
	   return prefix;
	}
	
	public static String longestCommonPrefix(String[] strs) {
	    if (strs == null || strs.length == 0) return "";
	    for (int i = 0; i < strs[0].length() ; i++){
	        char c = strs[0].charAt(i);
	        for (int j = 1; j < strs.length; j ++) {
	            if (i == strs[j].length() || strs[j].charAt(i) != c)
	                return strs[0].substring(0, i);             
	        }
	    }
	    return strs[0];
	}
	
/*	public static String LongestCommonPre(String [] strs){
		if(strs.length == 1 ){
			return strs[0].toString();
		}
		List <String> list1 = new ArrayList<>();
		List <String> list2 = new ArrayList<>();
		List <String> list3 = new ArrayList<>();
		int flag = 0;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		for(String str : strs){
			flag++;
			for(int i=0;i<str.length();i++){
				if(flag == 1){
					list1.add(String.valueOf(str.charAt(i)));
					flag1 = true;
				}else if (flag ==2){
					list2.add(String.valueOf(str.charAt(i)));
					flag2 = true;
				}else {
					list3.add(String.valueOf(str.charAt(i)));
					flag3 = true;
				}
			}
		}
		
		if(flag1){
			if(flag2){
				list1.retainAll(list2);
			}
			if(flag3){
				list1.retainAll(list3);
			}
		}
		
		String result = "";
		if(list1.size()>0){
			for(String str : list1){
				result += str;
			}
			return result;
		}else {
			return "";
		}
	}*/

}
