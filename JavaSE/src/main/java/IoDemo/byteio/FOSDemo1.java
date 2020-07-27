package IoDemo.byteio;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * java.io.FileOutputStream
 * 文件字节输出流，是一个低级流，
 * 用于向文件中写出字节
 * @author maxin
 *
 */
public class FOSDemo1 {
		public static void main(String[] args) throws IOException {
			/**
			 * 默认的构造方法是覆盖写操作，即：
			 * 若要写出的文件已经存在，会先将该文件
			 * 中的原有数据全部清除，然后再通过该流
			 * 写出新的数据
			 */
			//	FileOutputStream fos = new FileOutputStream("fos.txt");
			/**
			 * 追加写操作，该构造方法需要传入第二个
			 * 参数，该参数为一个boolean值，若该值
			 * 为true，则具有追加写操作的能力，那么
			 * 通过该流写出的内容会被追加到该文件
			 * 末尾
			 */
			FileOutputStream fos = new FileOutputStream("fos.txt",true);
					fos.write("哈哈".getBytes());
					System.out.println("写出完毕");
					fos.close();
		}
}
