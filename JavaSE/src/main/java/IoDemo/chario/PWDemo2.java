package IoDemo.chario;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * PW ����������
 * @author maxin
 *
 */
public class PWDemo2 {
		public static void main(String[] args) throws IOException {
			/**
			 * ���ļ�pw1.txt��д�����ݡ�
			 */
			FileOutputStream fos = new FileOutputStream("pw1.txt");
			/**
			 * PrintWriter ���췽��������ֽ����Ļ���
			 * ����ָ���ַ�����
			 * 
			 * ��ϣ��ָ���ַ�������Ҫ���м�ʹ��
			 * OutputStreamWriter��
			 */
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			PrintWriter pw = new PrintWriter(osw);    //�ڲ������˶�β���
			
			pw.println("���");
			pw.println("����");
			System.out.println("д�����");
			pw.close();
			
			
		}
}
