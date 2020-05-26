package xmlread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class WriteXmlDemo1 {
    public static void main(String[] args) throws IOException {
        List<Emp> list = new ArrayList<Emp>();
        list.add(new Emp(1, "a", 22, "m", 3000));
        list.add(new Emp(1, "b", 28, "w", 4000));
        list.add(new Emp(1, "c", 45, "m", 5000));
        list.add(new Emp(1, "d", 35, "w", 7000));

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("list");

        for (Emp emp : list) {
            Element empEle = root.addElement("emp");
            Element nameEle = empEle.addElement("name");
            nameEle.addText(emp.getName());

            Element ageEle = empEle.addElement("age");
            ageEle.addText(String.valueOf(emp.getAge()));

            Element genderEle = empEle.addElement("gender");
            genderEle.addText(emp.getGender());

            Element salaryEle = empEle.addElement("salary");
            salaryEle.addText(String.valueOf(emp.getSalary()));

            empEle.addAttribute("id", String.valueOf(emp.getId()));

        }
        XMLWriter writer = new XMLWriter();
        try {
            writer = new XMLWriter(OutputFormat.createPrettyPrint());
            FileOutputStream fos = new FileOutputStream("myemp.xml");
            writer.setOutputStream(fos);

            writer.write(document);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
