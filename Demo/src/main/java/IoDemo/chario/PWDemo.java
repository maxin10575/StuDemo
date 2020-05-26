package IoDemo.chario;
/**
 * 缓冲字符流
 * 内部维护缓冲区（字符数组），读写字符效率高
 * 并且可以按行读写字符串
 * Bufferedwriter , BufferedReader
 * 
 * java.io.PrintWriter
 * 常用的缓冲字符流，内部自动处理BufferedWriter
 * 来完成缓冲操作，并且PrintWtiter具有自动行刷新
 * 功能。
 * @author maxin
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public  class PWDemo{
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
	
/**
		 * Printwriter 提供了丰富的构造方法
		 * 其中提供了可以针对文件写出操作的
		 * 构造方法：
		 * PrintWriter(String path)
		 * PrintWriter(File file)
		 */
		PrintWriter  pw = new PrintWriter("pw.txt","UTF-8");
		
		pw.println("one");  //换行符/n占两个字节。
		pw.println("two");
		pw.println("three");
		pw.println("four");
		System.out.println("写出完毕");
		pw.close();
			
}
}
