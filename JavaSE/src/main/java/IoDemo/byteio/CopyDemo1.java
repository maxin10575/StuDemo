package IoDemo.byteio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ʹ���ļ�������ļ����Ʋ���
 * 
 * @author maxin
 *
 */
public class CopyDemo1 {
		public static void main(String[] args) throws IOException {
			/**
			 * ʹ��FIS��ȡԴ�ļ���ʹ��FOS��Ŀ���ļ���д���ݡ�
			 * ���δ�Դ�ļ��ж�ȡ�ֽ�Ȼ��д��Ŀ���ļ�����ɸ��Ʋ���
			 */
			FileInputStream fis = new FileInputStream("fos.txt");
			FileOutputStream fos = new FileOutputStream("fos_copy.txt");
			byte[] buf = new byte[1024*10];
			int len = -1;	
			while((len=fis.read(buf))!=-1){
				fos.write(buf, 0, len);
			}
			System.out.println("�������");
			fis.close();
			fos.close();
		}
}
