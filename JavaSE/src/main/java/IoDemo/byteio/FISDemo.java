package IoDemo.byteio;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * java.io.FileInputStream
 * �ļ��ֽ�����������һ���ͼ��������Դ�ָ��
 * �ļ��ж�ȡ�ֽڡ�
 * @author maxin
 *
 */
public class FISDemo {
		public static void main(String[] args) throws IOException {
			FileInputStream fis  = new FileInputStream("fos.txt");
			byte[] data = new byte[50];
			int len = fis.read(data);
			//String str = new String(data, 0, len,"utf-8");  //��ȡ��Ҫ�ö�Ӧ�ַ����������������
			String str = new String(data, 0, len);
			System.out.println(str);
			fis.close();
		}
}
