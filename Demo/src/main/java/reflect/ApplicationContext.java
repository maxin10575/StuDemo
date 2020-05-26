package reflect;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {
	private Map<String,Object> beans;
	public ApplicationContext(String xml) throws DocumentException, Exception{
		//解析XML，获取类名，根据类名创建Bean
		beans = new HashMap<String,Object>();
	
		SAXReader reader = new SAXReader();
		//从包中读取流
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(xml);
		Document doc = reader.read(in);
		//根元素<beans>子元素<bean id class>
		Element root = doc.getRootElement();
		//获取全部子元素
		List<Element> list = root.elements();
		//遍历每个bean，每个bean创建一个对象
		for(Element bean : list){
			//获取bean的ID和Class
			String id = bean.attributeValue("id");
			String className = bean.attributeValue("class");
			System.out.println(id+","+className);
			//利用反射API创建对象
			Class cls = Class.forName(className);
			Object obj = cls.newInstance();
			//缓存到 map 中
			beans.put(id, obj);
		}
	}
	
	public Object getBean(String id){
		//根据类名返回Bean对象
		return beans.get(id);
	}
}	
