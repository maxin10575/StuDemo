package IoDemo.chario;
/**
 * java.io.OutputStreamWriter
 * 字符输出流
 * @author maxin
 *
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * java 根据读写数据单位不同，将流分为：
 * 字节流与字符流
 * 字节流的最小读写单位为1个字节
 * 字符流的最小读写单位为1个字符
 * 
 * 字符流虽然是以字符为单位，但是底层实际上还是
 * 要以字节形式读写，所以字符流天生就具备将字节
 * 转换为字符或字符转换为字节的能力。所以所有的
 * 字符流都是高级流。方便我们读写字符数据。无需
 * 再关心字符与字节的相互转换了。
 * @author maxin
 *
 */
public class OSWDemo {
		public static void main(String[] args) throws IOException {
			 FileOutputStream fos = new FileOutputStream("osw.txt");
			 /**
			  * OutputStreamWriter 的常用构造方法：
			  * OutputStreamWriter(OutputStream out)
			  * 
			  * OutputStreamWriter(OutputStream out,String csn)
			  * 将给定的字节输出流转换为字符流的同时，指定
			  * 通过当前字符输出流写出的字符数据以何种字符集
			  * 转换为字节。
			  */
			 OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
		//	 OutputStreamWriter osw =new OutputStreamWriter(fos);

			for(int i =100000;i>=0;i--){
				osw.write(i+"\n");

			}
//				osw.write("哈哈");
			 System.out.println("写出完毕");
			 osw.close();
		}
}
