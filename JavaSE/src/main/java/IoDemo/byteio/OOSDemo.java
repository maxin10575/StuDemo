package IoDemo.byteio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * java.io.ObjectOutputStream
 * 对象输出流，是一个高级流，作用是可以直接将JAVA
 * 中的一个对象转换为一组字节后写出，这组字节的格式
 * 有OOS维护。
 * @author maxin
 *
 */
public class OOSDemo {
		public static void main(String[] args) throws IOException {
				Person p = new Person();
				p.setName("老师");
				p.setAge(25);
				p.setGender("女");
				
				List<String> otherInfo = new ArrayList<String>();	
					otherInfo.add("是一名演员");
					otherInfo.add("爱好是写字");
					otherInfo.add("促进交流");
					otherInfo.add("启蒙老师");
					p.setOtherInfo(otherInfo);
				System.out.println(p);
				
				FileOutputStream fos = new FileOutputStream("person.obj");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				/**
				 * OOS 的writeObject 方法的作用：
				 * 将给定的JAVA对象转换为一组字节后写出，这里由于OOS是装在FOS上的
				 * ，所以转换的这组字节最终通过ＦＯＳ写入到了文件person.obj中。
				 * 
				 * 若希望该对象可以被写出，那么前提是该对象
				 * 所属的类必须实现serializable接口。
				 * 
				 * 该方法涉及到了两个操作：
				 * 1，将对象转换为了一组字节
				 * 		上面的操作称为对象序列化
				 * 2，将该组字节写入到文件中（硬盘上）
				 *			上面的操作称为数据持久化。
				 */
				oos.writeObject(p);
				System.out.println("写出完毕");
				oos.close();			
		}

}
