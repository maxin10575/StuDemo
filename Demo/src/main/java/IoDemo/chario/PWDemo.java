package IoDemo.chario;
/**
 * �����ַ���
 * �ڲ�ά�����������ַ����飩����д�ַ�Ч�ʸ�
 * ���ҿ��԰��ж�д�ַ���
 * Bufferedwriter , BufferedReader
 * 
 * java.io.PrintWriter
 * ���õĻ����ַ������ڲ��Զ�����BufferedWriter
 * ����ɻ������������PrintWtiter�����Զ���ˢ��
 * ���ܡ�
 * @author maxin
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public  class PWDemo{
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
	
/**
		 * Printwriter �ṩ�˷ḻ�Ĺ��췽��
		 * �����ṩ�˿�������ļ�д��������
		 * ���췽����
		 * PrintWriter(String path)
		 * PrintWriter(File file)
		 */
		PrintWriter  pw = new PrintWriter("pw.txt","UTF-8");
		
		pw.println("one");  //���з�/nռ�����ֽڡ�
		pw.println("two");
		pw.println("three");
		pw.println("four");
		System.out.println("д�����");
		pw.close();
			
}
}
