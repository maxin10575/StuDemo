package test;

import java.util.Properties;

/**
 * @program: jsp
 * @description: x.properties读取
 * @author: maxin
 * @create: 2020-03-27 10:13
 * @Modified By:
 * @Version: 1.0
 **/
public class PropertiesRead {
    Properties prop = new Properties();
        //FileInputStream默认从项目根目录下读取文件，但是现在是web项目
        //文件放在resource下，编译时被编译到了classes下，所以需要从类路径下(classes)下读取此文件。
//			prop.load(new FileInputStream("config.properties"));
//        prop.load(DBUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        String classname = prop.getProperty("classname");
        String url = prop.getProperty("url");
}
