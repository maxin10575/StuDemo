package IoDemo.byteio;

import java.io.Serializable;
import java.util.List;

/**
 * �������ڲ�����Ϊ������ж������Ķ�д����
 * @author maxin
 *
 */	
public  class Person implements Serializable{ //ʵ�ֽӿں�ɱ����л�
	/**
	 * ���л��汾�ţ�
	 * ��һ����ʵ����Sericalizable�ӿں󣬸���
	 * ����һ��������ʾ�����İ汾�ţ��汾��Ӱ��
	 * �Ŷ������л��Ľ����
	 * ��������ά���汾��(�Լ�����ó���������ֵ������ָ��������������ݵ�ǰ��Ľṹ
	 * ����һ���汾�ţ��ṹ����汾�Ų��䣬���ǽṹ���ˣ��������ͣ����ֵ绰�ȣ�������
	 * ���°汾�Ÿı䡣
	 * 
	 * �����л�����ʱ������ö���İ汾���뵱ǰ�����ڵİ汾���Ƿ�һ�£�һ������Ի�ԭ����һ��
	 * �����л�ʧ�ܡ�
	 * 
	 * �汾��һ��ʱ�����㷴���л��Ķ����뵱ǰ��Ľṹ�г��룬Ҳ���ȡ����ģʽ��
	 * ������Ȼ�е����Ծͽ��л�ԭ��û�е������򱻺��ԡ�
	 * 
	 */
	private static final long serialVersionUID = 2L;


			private String name;
			private int age ;
			private String gender;
			/**
			 * transient �ؼ��ֵ�����������һ������
			 * ��ô��������ĳ��ʵ���������л�ʱ��������
			 * ���ᱻ���������л�����ֽ��У��Ӷ��ﵽ�˶���������Ŀ�ġ�
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

