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
		//����XML����ȡ������������������Bean
		beans = new HashMap<String,Object>();
	
		SAXReader reader = new SAXReader();
		//�Ӱ��ж�ȡ��
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(xml);
		Document doc = reader.read(in);
		//��Ԫ��<beans>��Ԫ��<bean id class>
		Element root = doc.getRootElement();
		//��ȡȫ����Ԫ��
		List<Element> list = root.elements();
		//����ÿ��bean��ÿ��bean����һ������
		for(Element bean : list){
			//��ȡbean��ID��Class
			String id = bean.attributeValue("id");
			String className = bean.attributeValue("class");
			System.out.println(id+","+className);
			//���÷���API��������
			Class cls = Class.forName(className);
			Object obj = cls.newInstance();
			//���浽 map ��
			beans.put(id, obj);
		}
	}
	
	public Object getBean(String id){
		//������������Bean����
		return beans.get(id);
	}
}	
