package test;

public class SysPropertyTest {
	    static {
	        setValue();
	    }
	    public static void setValue() {
	        System.setProperty("name", "张三");
	        System.setProperty("age", "28");
	    }
	    
	    public static void main(String[] args) {
	        System.out.println(System.getProperty("name"));
	        System.out.println(System.getProperty("age"));
	        
	    }
	
}
