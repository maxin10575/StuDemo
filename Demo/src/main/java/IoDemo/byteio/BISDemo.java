package IoDemo.byteio;

import java.io.*;

/**
 * ������
 * ��������һ�ָ߼�����ʹ�����ǽ��ж�д��Ч���ǱȽϸߵġ�
 * @author maxin
 *
 */
public class BISDemo {
		public static void main(String[] args) throws IOException {
			FileInputStream fis = new FileInputStream("fos.txt");
			//��BISװ��FIS�Ͽ�������ļ���ȡЧ�ʡ�
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			FileOutputStream fos = new FileOutputStream("fos_copy.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			int d = -1;	
			/**
			 * �������ڲ�ά����һ���������������ǵ���
			 * ����read()������ȡһ���ֽ�ʱ��ʵ���ϻ���
			 * ������fis��ȡһ���ֽڲ����뵽�����������ڲ�
			 * ���ֽ������У�Ȼ�󽫵�һ���ֽڷ��ء��������ٴε��ø�read����
			 * ������ȡһ���ֽ�ʱ����������ֱ�ӽ������еڶ����ֽڷ��أ��Դ����ƣ�ֱ��
			 * �������е������ֽڶ�����ȡ����Ż��ٴζ�ȡһ���ֽڡ�
			 * ����ʵ���ϻ���ͨ�����ÿ�ζ�ȡ���ݵ����������ٶ�ȡ�Ĵ������
			 * �Ķ�ȡЧ�ʡ�
			 */
			while((d=bis.read())!=-1){
				bos.write(d);
			}
			System.out.println("�������");
			fis.close();
			fos.close();
		}
}
