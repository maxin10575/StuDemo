package test;

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
//        Qw ha	= new Qw();
//		He he = new He();
//        System.out.println(new zi());


		Heimp heimp = new Heimp(1);
        heimp.testHa3();
        He.testHe1();
		heimp.testHe2();
		heimp.testHa1();


//		heimp.testHeimp();
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
    static{
        System.out.println("Ha===父类静态代码块");
    };
    Ha() {
        System.out.println("Ha===抽象父类 无参构造器");
    };
    Ha(String a) {
        System.out.println("Ha===抽象父类 有参构造器");
        this.a = a;
    }
	public abstract void testHa2();
    public abstract void testHa1();
    public static void testHa3() {
        System.out.println("Ha===抽象父类 test3");
    }
    public void testHa4(){
        System.out.println("Ha===抽象父类 test1");
    }
}

class Qw extends Ha {
    Qw(){
        super();
        testHa3();
        System.out.println("Qw===子类无参构造器");
    }

    @Override
	public void testHa2() {
	}

    @Override
    public void testHa1() {

    }

}

interface He {
    public static void testHe1(){System.out.println("父类He==test1");}
	public abstract void testHe3();
	public  void testHe2();
}

class Heimp extends  Ha implements He{
    Heimp(){
        super("1");
    }
    Heimp(int a){
        super("2");
        System.out.println("Heiimp  有参构造器");
    }


    public static void testHeimp(){
        He.testHe1();
    }

    @Override
    public void testHa2() {

    }

    @Override
    public void testHa1() {

    }

    @Override
    public void testHa4() {

    }

	@Override
	public void testHe3() {
	}

    @Override
    public void testHe2() {

    }


}


