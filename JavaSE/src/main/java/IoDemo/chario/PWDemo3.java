package IoDemo.chario;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * �Զ���ˢ��
 * ��PW�������һ����ʱ�����췽��������ڶ���
 * �������ò���Ϊһ��booelanֵ������ֵΪtrueʱ��
 * ������Զ���ˢ�¹��ܣ�����ÿ��ʹ��println����
 * д��һ���ַ���ʱ���Զ�flush.
 * @author maxin
 *
 */
public class PWDemo3 {
		public static void main(String[] args) throws IOException {
			/**
			 * ���±�����
			 */
			Scanner  scanner = new Scanner(System.in);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("note.txt")) ,true);
			
			System.out.println("�뿪ʼ�������ݣ�");
			while(true){
				String line  = scanner.nextLine();
				if("exit".equals(line)){
					System.out.println("�ټ���");
					break;
				}
				pw.println(line);
			}
			pw.close();
			scanner.close();
		}
}
