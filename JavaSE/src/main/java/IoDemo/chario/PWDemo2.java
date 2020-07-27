package IoDemo.chario;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * PW 处理其他流
 * @author maxin
 *
 */
public class PWDemo2 {
		public static void main(String[] args) throws IOException {
			/**
			 * 向文件pw1.txt总写出内容。
			 */
			FileOutputStream fos = new FileOutputStream("pw1.txt");
			/**
			 * PrintWriter 构造方法传入的字节流的话，
			 * 不能指定字符集。
			 * 
			 * 若希望指定字符集，需要在中间使用
			 * OutputStreamWriter。
			 */
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			PrintWriter pw = new PrintWriter(osw);    //内部进行了多次操作
			
			pw.println("你好");
			pw.println("哈哈");
			System.out.println("写出完毕");
			pw.close();
			
			
		}
}
