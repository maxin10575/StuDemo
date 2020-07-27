package IoDemo.chario;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java.io.InputStreamReader
 * 字符输入流
 * @author maxin
 *除了ISR与OSW之外的字符流，大部分都只处理其他字符流。但是低级流
 *都是字节流，这时若希望用一个字符流来处理字节流就是产生了冲突。
 *所以可以通过创建ISR或ＯＳＷ来处理字节流，而ISR和OSW本身是字符流
 *，所以可以使得其他字符流得以处理该流。
 *ISR与OSW相当于是联系字节流与字符流的纽带，类似
 *转换器的效果。
 */
public class ISRDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("osw.txt");
		InputStreamReader isr =new InputStreamReader(fis);
		/**
		 * int read（）
		 * 一次读取一个字符，若返回值为-1 ，则表示
		 * 读到末尾
		 */
		int d =-1;
		while((d=isr.read())!=-1){
			System.out.println((char)d);
		}
		isr.close();
	}
}
