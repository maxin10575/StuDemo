package algorithm;

import java.util.HashMap;
import java.util.Stack;

import org.junit.Test;

public class Vaildparenthese {
	
	private HashMap<Character, Character> mappings;

	 public Vaildparenthese(){
		 this.mappings = new HashMap<Character, Character>();
		    this.mappings.put(')', '(');
		    this.mappings.put('}', '{');
		    this.mappings.put(']', '[');
	 }
	 
	 @Test
	 public void test() {
		String s = "[()]{}";
		System.out.println(isValid2(s));

	}


	 boolean isValid2(String s) {
		    Stack<Character> stack = new Stack<Character>();
		    for (int i = 0; i < s.length(); i++) {
		      char c = s.charAt(i);
		      if (mappings.containsKey(c)) {

		        char topElement = stack.empty() ? '#' : stack.pop();

		        if (topElement != this.mappings.get(c)) {
		          return false;
		        }
		      } else {
		        stack.push(c);
		      }
		    }
		    return stack.isEmpty();
		  }
	
	
	
	  boolean isValid(String s){
		   
	        while (s.contains("{}")||s.contains("[]")|| s.contains("()")){
	            if(s.contains("{}")) s=s.replace("{}","");
	            if(s.contains("()")) s=s.replace("()","");
	            if(s.contains("[]")) s=s.replace("[]","");
	        }
	        
	        return s.isEmpty();
	}
	
	
}
