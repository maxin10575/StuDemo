package IoDemo.chario;

import java.io.*;

/**
 * java.io.BufferedReader
 * 缓冲字符输入流，特点：按行读取字符串
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
			 * 连续读取若干字符，直到读取换行符为止，
			 * 将换行符之前的所有字符以一个字符串返回，
			 * 若该方法返回值为null，则表示读取到了末尾，
			 * 注意，返回的字符串中不含有读取该行内容时最后的换行符。
			 */
			String line = null;
			while((line = br.readLine())!=null){
				System.out.println(line);
			}
			br.close();
		}
}
