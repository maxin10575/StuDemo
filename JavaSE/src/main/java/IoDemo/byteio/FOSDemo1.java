package IoDemo.byteio;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * java.io.FileOutputStream
 * �ļ��ֽ����������һ���ͼ�����
 * �������ļ���д���ֽ�
 * @author maxin
 *
 */
public class FOSDemo1 {
		public static void main(String[] args) throws IOException {
			/**
			 * Ĭ�ϵĹ��췽���Ǹ���д����������
			 * ��Ҫд�����ļ��Ѿ����ڣ����Ƚ����ļ�
			 * �е�ԭ������ȫ�������Ȼ����ͨ������
			 * д���µ�����
			 */
			//	FileOutputStream fos = new FileOutputStream("fos.txt");
			/**
			 * ׷��д�������ù��췽����Ҫ����ڶ���
			 * �������ò���Ϊһ��booleanֵ������ֵ
			 * Ϊtrue�������׷��д��������������ô
			 * ͨ������д�������ݻᱻ׷�ӵ����ļ�
			 * ĩβ
			 */
			FileOutputStream fos = new FileOutputStream("fos.txt",true);
					fos.write("����".getBytes());
					System.out.println("д�����");
					fos.close();
		}
}
