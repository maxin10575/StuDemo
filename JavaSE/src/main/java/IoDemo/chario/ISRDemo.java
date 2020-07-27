package IoDemo.chario;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java.io.InputStreamReader
 * �ַ�������
 * @author maxin
 *����ISR��OSW֮����ַ������󲿷ֶ�ֻ���������ַ��������ǵͼ���
 *�����ֽ�������ʱ��ϣ����һ���ַ����������ֽ������ǲ����˳�ͻ��
 *���Կ���ͨ������ISR��ϣӣ��������ֽ�������ISR��OSW�������ַ���
 *�����Կ���ʹ�������ַ������Դ��������
 *ISR��OSW�൱������ϵ�ֽ������ַ�����Ŧ��������
 *ת������Ч����
 */
public class ISRDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("osw.txt");
		InputStreamReader isr =new InputStreamReader(fis);
		/**
		 * int read����
		 * һ�ζ�ȡһ���ַ���������ֵΪ-1 �����ʾ
		 * ����ĩβ
		 */
		int d =-1;
		while((d=isr.read())!=-1){
			System.out.println((char)d);
		}
		isr.close();
	}
}
