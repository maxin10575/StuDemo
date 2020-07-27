package test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Fuimlp {
    public static void main(String[] args) {
//		Map<String, Object> map = new HashMap<>();
//		map.put(null,"3");
//		map.put("3",null);
//		System.out.println("hashMap的[key]和[value]均可以为null:" + map.get(null));
//		System.out.println("hashMap的[key]和[value]均可以为null:" + map.get(3));
		/*Map<String, String> tableMap = new Hashtable<String, String>();//HashTable对象
		tableMap.put(null,"3");
		tableMap.put("3", null);
		System.out.println("tableMap.get(null)===="+tableMap.get(null));
		System.out.println("tableMap.get(3)====="+tableMap.get("3"));*/


//		Ha ha	= new Ha();
//		He he = new He();
//        System.out.println(new zi());


		Heimp heimp = new Heimp();
		heimp.test1();
    }
}

class zi extends Fu {
    int a;

    public zi(int b) {
        super(3);
        System.out.println("子类有参构造器" + b);
    }

    public zi() {
        super(1);
        System.out.println("子类无参构造器");
    }
}


abstract class Ha {
    private String a;
    Ha() { };
    Ha(String a) {
        this.a = a;
    }
	public abstract void test2();
    public abstract void test();
    public void test1(){
	}
}

abstract class Qw extends Ha {
	@Override
	public void test() {
	}
}

interface He {
	public static void test1(){System.out.println("1111");}
	public abstract void test3();
	public  void test2();
}

class Heimp implements He{

	public static void test1(){
				He.test1();
	}



	@Override
	public void test3() {
	}

	@Override
	public void test2() {
	}
}


