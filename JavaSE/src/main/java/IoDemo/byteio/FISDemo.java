package IoDemo.byteio;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * java.io.FileInputStream
 * 文件字节输入流，是一个低级流，可以从指定
 * 文件中读取字节。
 * @author maxin
 *
 */
public class FISDemo {
		public static void main(String[] args) throws IOException {
			FileInputStream fis  = new FileInputStream("fos.txt");
			byte[] data = new byte[50];
			int len = fis.read(data);
			//String str = new String(data, 0, len,"utf-8");  //读取需要用对应字符集。否则出现乱码
			String str = new String(data, 0, len);
			System.out.println(str);
			fis.close();
		}
}
