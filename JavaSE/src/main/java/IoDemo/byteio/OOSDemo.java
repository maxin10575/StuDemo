package IoDemo.byteio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * java.io.ObjectOutputStream
 * �������������һ���߼����������ǿ���ֱ�ӽ�JAVA
 * �е�һ������ת��Ϊһ���ֽں�д���������ֽڵĸ�ʽ
 * ��OOSά����
 * @author maxin
 *
 */
public class OOSDemo {
		public static void main(String[] args) throws IOException {
				Person p = new Person();
				p.setName("��ʦ");
				p.setAge(25);
				p.setGender("Ů");
				
				List<String> otherInfo = new ArrayList<String>();	
					otherInfo.add("��һ����Ա");
					otherInfo.add("������д��");
					otherInfo.add("�ٽ�����");
					otherInfo.add("������ʦ");
					p.setOtherInfo(otherInfo);
				System.out.println(p);
				
				FileOutputStream fos = new FileOutputStream("person.obj");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				/**
				 * OOS ��writeObject ���������ã�
				 * ��������JAVA����ת��Ϊһ���ֽں�д������������OOS��װ��FOS�ϵ�
				 * ������ת���������ֽ�����ͨ���ƣϣ�д�뵽���ļ�person.obj�С�
				 * 
				 * ��ϣ���ö�����Ա�д������ôǰ���Ǹö���
				 * �����������ʵ��serializable�ӿڡ�
				 * 
				 * �÷����漰��������������
				 * 1��������ת��Ϊ��һ���ֽ�
				 * 		����Ĳ�����Ϊ�������л�
				 * 2���������ֽ�д�뵽�ļ��У�Ӳ���ϣ�
				 *			����Ĳ�����Ϊ���ݳ־û���
				 */
				oos.writeObject(p);
				System.out.println("д�����");
				oos.close();			
		}

}
