package IoDemo.chario;
/**
 * java.io.OutputStreamWriter
 * �ַ������
 * @author maxin
 *
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * java ���ݶ�д���ݵ�λ��ͬ��������Ϊ��
 * �ֽ������ַ���
 * �ֽ�������С��д��λΪ1���ֽ�
 * �ַ�������С��д��λΪ1���ַ�
 * 
 * �ַ�����Ȼ�����ַ�Ϊ��λ�����ǵײ�ʵ���ϻ���
 * Ҫ���ֽ���ʽ��д�������ַ��������;߱����ֽ�
 * ת��Ϊ�ַ����ַ�ת��Ϊ�ֽڵ��������������е�
 * �ַ������Ǹ߼������������Ƕ�д�ַ����ݡ�����
 * �ٹ����ַ����ֽڵ��໥ת���ˡ�
 * @author maxin
 *
 */
public class OSWDemo {
		public static void main(String[] args) throws IOException {
			 FileOutputStream fos = new FileOutputStream("osw.txt");
			 /**
			  * OutputStreamWriter �ĳ��ù��췽����
			  * OutputStreamWriter(OutputStream out)
			  * 
			  * OutputStreamWriter(OutputStream out,String csn)
			  * ���������ֽ������ת��Ϊ�ַ�����ͬʱ��ָ��
			  * ͨ����ǰ�ַ������д�����ַ������Ժ����ַ���
			  * ת��Ϊ�ֽڡ�
			  */
			 OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");
		//	 OutputStreamWriter osw =new OutputStreamWriter(fos);
			 
			 osw.write("���");
			 osw.write("����");
			 System.out.println("д�����");
			 osw.close();
		}
}
