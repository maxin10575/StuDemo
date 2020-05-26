package algorithm;
/**
 * 
 * @author Godxi
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 */
public class PalindromeNumber {

	public static void main(String[] args) {
		int num = 1;
		//        0123456
		boolean result = isPalindrome(num);
		System.out.println(result);
	}
	
	public static boolean isPalindrome(int num){
		boolean flag = false;
		String numstr = String.valueOf(num);
		if(numstr.length() == 1){
			flag = true;
		}else {
		 for(int i=0;i<numstr.length()/2;i++){
			char startStr = numstr.charAt(i);
			char endStr = numstr.charAt(numstr.length()-i-1);
			if( startStr == endStr ){
				flag = true;
			}else{
				flag = false;
				break;
			}
		 }
		}
		
		return flag;
	}

}
