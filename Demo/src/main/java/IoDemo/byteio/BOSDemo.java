package IoDemo.byteio;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ���������д������
 * @author maxin
 *
 */
public class BOSDemo {
		public static void main(String[] args) throws IOException {
			FileOutputStream fos = new FileOutputStream( "bos.txt");
			
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			/**
			 * ͨ�����������д�����ֽڲ��������̱�д���ļ������ȴ������ڲ����ֽ����飬
			 * ֪�����������ˣ��Ż�һ����д���������ݡ���������ͬ�����д������������д��
			 * �������д��Ч��
			 */
			bos.write("��ð�������".getBytes());
			System.out.println("д�����");
			/**
			 * flush��������ǿ�ƽ���������������һ����
			 * д��������������߼�ʱ�ԣ�����Ƶ�������ᵼ��д���������
			 * ����д��Ч�ʡ�
			 */
			bos.flush();
			bos.close();
		}
}
