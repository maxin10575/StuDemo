package IoDemo.chario;
/**
 * �õ�ǰ������쳣���׳�
 * @author maxin
 *
 */
public class Person{
	private int age;
	
	public int getAge(){
		return age;
	}
	/**
	 * ��һ��������ʹ��throw�׳�ĳ���쳣ʱ����Ҫ�ڵ�ǰ������ʹ��throws
	 * �����÷��������׳����쳣���Ա���֪ͨ�����ߴ�����쳣��
	 * ֻ��һ���쳣�׳�ʱ����������ǿ��Ҫ���ڷ�����ʹ��throws�������쳣���׳���
	 * ���ǣ�RuntimeException ���������쳣��
	 * @param age
	 * @throws Exception
	 */
	public void setAge(int age) throws Exception {
		if(age<0||age>100){
			/**
			 * �����׳��쳣�����֮һ��
			 * �����﷨Ҫ�󣬵��ǲ�����ҵ���߼�Ҫ��
			 */
//			throw new IllegalAgeException("���䲻�Ϸ�");
		}
		this.age =age;
	}
}
