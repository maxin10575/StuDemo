package IoDemo.byteio;

import java.io.Serializable;
import java.util.List;

/**
 * 该类用于测试作为对象进行对象流的读写操作
 * @author maxin
 *
 */	
public  class Person implements Serializable{ //实现接口后可被序列化
	/**
	 * 序列化版本号：
	 * 当一个类实现了Sericalizable接口后，该类
	 * 会有一个常量表示这个类的版本号，版本号影响
	 * 着对象反序列化的结果。
	 * 建议自行维护版本号(自己定义该常量并给定值），不指定，编译器会根据当前类的结构
	 * 生成一个版本号，结构不变版本号不变，但是结构变了（属性类型，名字电话等，）都会
	 * 导致版本号改变。
	 * 
	 * 反序列化对象时，会检查该对象的版本号与当前类现在的版本号是否一致，一致则可以还原，不一致
	 * 则反序列化失败。
	 * 
	 * 版本号一致时，就算反序列化的对象与当前类的结构有出入，也会采取兼容模式，
	 * 即：仍然有的属性就进行还原，没有的属性则被忽略。
	 * 
	 */
	private static final long serialVersionUID = 2L;


			private String name;
			private int age ;
			private String gender;
			/**
			 * transient 关键字的作用是修饰一个属性
			 * 那么当这个类的某个实例进行序列化时，该属性
			 * 不会被包含在序列化后的字节中，从而达到了对象“瘦身”的目的。
			 */
			private transient  List<String>otherInfo;	
			public Person(){}
			public Person(String name,int age,String gender,List<String>otherInfo){
				super();
				this.name =name;
				this.age =age;
				this.gender=gender;
				this.otherInfo =otherInfo;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public int getAge() {
				return age;
			}
			public void setAge(int age) {
				this.age = age;
			}
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
				this.gender = gender;
			}
			public List<String> getOtherInfo() {
				return otherInfo;
			}
			public void setOtherInfo(List<String> otherInfo) {
				this.otherInfo = otherInfo;
			}
			public String toString() {
				return "Person [name=" + name + ", age=" + age + ", gender="
						+ gender + ", otherInfo=" + otherInfo + "]";
			}
		
}

