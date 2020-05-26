package IoDemo.byteio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * java.io.ObjectInputSteram
 * ��������������һ���߼����������ǿ��Զ�ȡһ���ֽ�
 * Ȼ���仹ԭΪ�������Ķ���
 * ��ע�⣬��ȡ����Щ�ֽڱ�������ObjectOutputSteram
 * ��һ������ת�����ֽڣ�������׳��쳣��
 * @author maxin
 *
 */
public class OISDemo {
		public static void main(String[] args) throws IOException, ClassNotFoundException {
			FileInputStream fis = new FileInputStream("person.obj");
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			/**
			 * ��һ���ֽڻ�ԭΪ����Ĺ��̳�Ϊ��
			 * ����ķ����л���
			 */
			Person p = (Person)ois.readObject();
			System.out.println(p);
			ois.close();
		}
}
