package xmlread;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class test {


    protected void service(HttpServletRequest request, HttpServletResponse response){
        try{

            BufferedReader bufferreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String buffer = null;
            StringBuffer xmlsb = new StringBuffer();
            while ((buffer = bufferreader.readLine()) != null) {
                xmlsb.append(buffer);
            }

            Document document = null;
            SAXReader reader = new SAXReader();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlsb.toString().getBytes());
            InputStreamReader ir = new InputStreamReader(inputStream);
            document = reader.read(ir);
            Element root = document.getRootElement();
            List<Element> elements = root.elements("SSO");
            for (Element empEle : elements) {
                String resutlstr = empEle.element("RESULT").getText();
                System.out.println("RESULT==="+resutlstr);

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
