package IoDemo.chario;
/**
 * �����쳣�׳��Ľ��
 * @author maxin
 *
 */
public class TestPerson {
		public static void main(String[] args)  {
			Person p = new Person();
			/**
			 * ������һ�������쳣�׳������ķ���ʱ��������Ҫ����봦��÷�������
			 * �����׳����쳣�������ֶ������֣�
			 * 1��ʹ��try-catch���񲢽��
			 * 2���ڵ�ǰ�����ϼ���ʹ��throws�������쳣���׳���
			 * 
			 * ������main�����������쳣���׳���
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
