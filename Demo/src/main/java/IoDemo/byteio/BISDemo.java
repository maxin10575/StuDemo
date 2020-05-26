package IoDemo.byteio;

import java.io.*;

/**
 * 缓冲流
 * 缓冲流是一种高级流，使用它们进行读写的效率是比较高的。
 * @author maxin
 *
 */
public class BISDemo {
		public static void main(String[] args) throws IOException {
			FileInputStream fis = new FileInputStream("fos.txt");
			//将BIS装到FIS上可以提高文件读取效率。
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			FileOutputStream fos = new FileOutputStream("fos_copy.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			int d = -1;	
			/**
			 * 缓冲流内部维护了一个缓冲区，当我们调用
			 * 下面read()方法读取一个字节时，实际上缓冲
			 * 流会让fis读取一组字节并存入到缓冲流自身内部
			 * 的字节数组中，然后将第一个字节返回。当我们再次调用该read（）
			 * 方法读取一个字节时，缓冲流会直接将数组中第二个字节返回，以此类推，直到
			 * 该数组中的所有字节都被读取过后才会再次读取一组字节。
			 * 所以实际上还是通过提高每次读取数据的数量来减少读取的次数提高
			 * 的读取效率。
			 */
			while((d=bis.read())!=-1){
				bos.write(d);
			}
			System.out.println("复制完成");
			fis.close();
			fos.close();
		}
}
