package IoDemo.chario;

import java.io.*;

/**
 * java.io.BufferedReader
 * �����ַ����������ص㣺���ж�ȡ�ַ���
 * @author maxin
 *
 */
public class BRDemo {
		public static void main(String[] args) throws IOException {
			FileInputStream fis = new FileInputStream("."+File.separator+"src"+File.separator+"day08"+File.separator+"BRDemo.java");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			/**
			 * String readLine()
			 * ������ȡ�����ַ���ֱ����ȡ���з�Ϊֹ��
			 * �����з�֮ǰ�������ַ���һ���ַ������أ�
			 * ���÷�������ֵΪnull�����ʾ��ȡ����ĩβ��
			 * ע�⣬���ص��ַ����в����ж�ȡ��������ʱ���Ļ��з���
			 */
			String line = null;
			while((line = br.readLine())!=null){
				System.out.println(line);
			}
			br.close();
		}
}
