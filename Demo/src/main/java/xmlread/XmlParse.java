package xmlread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author xinma
 */
public class XmlParse {
    public static void main(String[] args) {

//		RandomAccessFile raf = null;
        try {

            SAXReader reader = new SAXReader();

            File file = new File("emplist.xml");

            Document doc = reader.read(file);

            Element root = doc.getRootElement();

            List<Emp> list = new ArrayList<Emp>();

            List<Element> elements = root.elements("emp"); // ����ȫ��emp��ǩ

            for (Element empEle : elements) {

                Element nameEle = empEle.element("name");
                /*
                 * Element idEle = empEle.element("id"); Element nameEle =
                 * empEle.element("name"); Element ageEle =
                 * empEle.element("age"); Element genderEle =
                 * empEle.element("gender"); Element salaryEle =
                 * empEle.element("salary"); 8
                 */
                /*
                 * Element �ṩ�˻�ȡ��ǰ��ǩ�м���ı���Ϣ String getText() String
                 * getTextTrim() lone
                 */
                String name = nameEle.getText();
                /*
                 * int age = Integer.parseInt(ageEle.getText()); String gender =
                 * genderEle.getText(); int salary =
                 * Integer.parseInt(salaryEle.getText()); int id =
                 * Integer.parseInt(idEle.getText());
                 * System.out.println(id+":"+name+":"+gender+","+age+","+salary)
                 * ;
                 */


                int age = Integer.parseInt(empEle.elementText("age"));
                String gender = empEle.elementText("gender");
                int salary = Integer.parseInt(empEle.elementText("salary"));

                Attribute attr = empEle.attribute("id");
                int id = Integer.parseInt(attr.getValue());
                Emp emp = new Emp(id, name, age, gender, salary);
                list.add(emp);
            }
            for (Emp e : list) {
                System.out.println(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
