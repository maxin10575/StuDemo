package IoDemo.chario;
/**
 * 测试异常抛出的解决
 * @author maxin
 *
 */
public class TestPerson {
		public static void main(String[] args)  {
			Person p = new Person();
			/**
			 * 当调用一个含有异常抛出声明的方法时，编译器要求必须处理该方法声明
			 * 可能抛出的异常，处理手段有两种：
			 * 1：使用try-catch捕获并解决
			 * 2，在当前方法上继续使用throws声明该异常的抛出。
			 * 
			 * 不能在main方法上声明异常的抛出。
			 */
			try {
				p.setAge(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(p.getAge());

		}
}
