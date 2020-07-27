package IoDemo.byteio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * java.io.ObjectInputSteram
 * 对象输入流，是一个高级流，作用是可以读取一组字节
 * 然后将其还原为其描述的对象。
 * 需注意，读取的这些字节必须是由ObjectOutputSteram
 * 将一个对象转换的字节，否则会抛出异常。
 * @author maxin
 *
 */
public class OISDemo {
		public static void main(String[] args) throws IOException, ClassNotFoundException {
			FileInputStream fis = new FileInputStream("person.obj");
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			/**
			 * 将一组字节还原为对象的过程称为：
			 * 对象的反序列化。
			 */
			Person p = (Person)ois.readObject();
			System.out.println(p);
			ois.close();
		}
}
