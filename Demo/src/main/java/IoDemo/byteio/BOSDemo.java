package IoDemo.byteio;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 缓冲输出流写出数据
 * @author maxin
 *
 */
public class BOSDemo {
		public static void main(String[] args) throws IOException {
			FileOutputStream fos = new FileOutputStream( "bos.txt");
			
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			/**
			 * 通过缓冲输出流写出的字节并不会立刻被写入文件，会先存入其内部的字节数组，
			 * 知道该数组满了，才会一次性写出所有数据。这样做等同于提高写出数据量减少写出
			 * 次数提高写出效率
			 */
			bos.write("你好啊啊啊啊".getBytes());
			System.out.println("写出完毕");
			/**
			 * flush方法可以强制将缓冲区已有数据一次性
			 * 写出，这样可以提高及时性，但是频繁操作会导致写出次数提高
			 * 降低写出效率。
			 */
			bos.flush();
			bos.close();
		}
}
