package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mx
 * @create  2021/04/08 14:14
 * @description 基于dom4j的工具包
 */
public class XmlUtil {
    private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    public static final String XML_ROOT = "ROOT";

    public static final String HEAD_LEFT = "<";

    public static final String FOOT_LEFT = "</";

    public static final String RIGHT = ">";

    public static Map<String,Object> xmlToMap(String xml) {
        Map<String, Object> map = new HashMap<>();
        //获取根节点元素对象
        Element root = getRootElement(xml);
        if(root != null) {
            elementToMap(root, map);
        }
        return map;
    }

    public static String mapToXML(Map<String,Object> map) {
        StringBuilder xmlStr = new StringBuilder();
        xmlStr.append(XML_HEAD).append(HEAD_LEFT).append(XML_ROOT).append(RIGHT);
        mapToElement(map, xmlStr);
        xmlStr.append(FOOT_LEFT).append(XML_ROOT).append(RIGHT);
        return xmlStr.toString();
    }

    /**
     * json转xml
     * @param jsonStr
     * @return
     */
    public static String jsonToXML(String jsonStr) {
        StringBuilder xmlStr = new StringBuilder();
        xmlStr.append(XML_HEAD).append(HEAD_LEFT).append(XML_ROOT).append(RIGHT);
        Object obj = JSON.parse(jsonStr);
        if (obj instanceof JSONObject) {
            JSONObject json = (JSONObject) obj;
            jsonToElement(json, xmlStr);
        }
        xmlStr.append(FOOT_LEFT).append(XML_ROOT).append(RIGHT);
        return xmlStr.toString();
    }

    /**
     * 将xml转换为JSON对象
     * @param xml xml字符串
     * @return
     * @throws Exception
     */
    public static JSONObject xmlToJson(String xml) {
        JSONObject json = new JSONObject();
        //获取根节点元素对象
        Element root = getRootElement(xml);
        if(root != null) {
            iterateNodes(root, json);
        }
        return json;
    }

    /**
     * 获得根据节点信息
     * @param xml xml字符串
     * @return
     */
    public static Element getRootElement(String xml){
        Element root = null;
        try {
            Document doc = DocumentHelper.parseText(xml);
            root = doc.getRootElement();
        } catch (Exception ex) {
            logger.error("解释xml文件出现异常:" + ex.getMessage());
        }
        return root;
    }

    /**
     * json转xml字符串
     * @param json
     * @param sb
     */
    private static void jsonToElement(JSONObject json, StringBuilder sb){
        if(json.size() > 0) {
            json.keySet().stream().forEach(key -> {
                Object value = json.get(key);
                if(value == null) {
                    value = "";
                }
                sb.append(HEAD_LEFT).append( key ).append(RIGHT);
                if (value instanceof JSONArray) {
                    JSONArray array = (JSONArray) value;
                    for(int i=0; i<array.size(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        jsonToElement(json, sb);
                    }
                } else {
                    if (value instanceof JSONObject) {
                        JSONObject object = (JSONObject) value;
                        jsonToElement(object, sb);
                    } else {
                        sb.append(value);
                    }
                }
                sb.append(FOOT_LEFT).append( key).append(RIGHT);
            });
        }
    }

    /**
     * map转XML字符串
     * @param map
     * @param sb
     */
    @SuppressWarnings("unchecked")
    private static void mapToElement(Map<String, Object> map, StringBuilder sb) {
        map.keySet().stream().forEach(key -> {
            Object value = map.get(key);
            if(value == null) {
                value = "";
            }
            if (value instanceof List<?>) {
                List<Object> list = (List<Object>)map.get(key);
                sb.append(HEAD_LEFT).append( key ).append(RIGHT);
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> temp = (Map<String, Object>)list.get(i);
                    mapToElement(temp, sb);
                }
                sb.append(FOOT_LEFT).append( key).append(RIGHT);
            } else {
                if (value instanceof Map<?, ?>) {
                    sb.append(HEAD_LEFT).append( key ).append(RIGHT);
                    mapToElement((Map<String, Object>)value, sb);
                    sb.append(FOOT_LEFT).append( key).append(RIGHT);
                } else {
                    sb.append(HEAD_LEFT).append( key ).append(RIGHT);
                    sb.append(value);
                    sb.append(FOOT_LEFT).append( key).append(RIGHT);
                }

            }
        });
    }

    /**
     * 递归方式
     * @param element
     * @param map
     */
    @SuppressWarnings("unchecked")
    private static void elementToMap(Element element, Map<String, Object> map) {
        if (element == null) {
            return;
        }
        String elemName =element.getName();
        //判断当前节点的内容是否为文本（最里面一层节点）
        if (element.isTextOnly()) {
            map.put(elemName, element.getText());
        } else {
            Map<String, Object> mapSub = new HashMap<>();
            //获取当前节点的所有子节点
            List<Element> elements = (List<Element>)element.elements();
            for (Element temp : elements) {
                elementToMap(temp, mapSub);
            }
            if(!map.containsKey(elemName)) {
                map.put(elemName, mapSub);
            } else {
                List<Object> listSub = new ArrayList<>();
                listSub.add(map.get(elemName));
                listSub.add(mapSub);
                map.put(elemName,listSub);
            }
        }

    }

    /**
     * 遍历元素
     * @param node 元素
     * @param json 将元素遍历完成之后放的JSON对象
     */
    @SuppressWarnings("unchecked")
    private static void iterateNodes(Element node, JSONObject json){
        //获取当前元素的名称
        String nodeName = node.getName();
        //判断已遍历的JSON中是否已经有了该元素的名称
        if(json.containsKey(nodeName)){
            //该元素在同级下有多个
            Object Object = json.get(nodeName);
            JSONArray array = null;
            if(Object instanceof JSONArray){
                array = (JSONArray) Object;
            }else {
                array = new JSONArray();
                array.add(Object);
            }
            //获取该元素下所有子元素
            List<Element> listElement = node.elements();
            if(listElement.isEmpty()){
                //该元素无子元素，获取元素的值
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
            } else {
                //有子元素
                JSONObject obj = new JSONObject();
                //遍历所有子元素
                for(Element e:listElement){
                    //递归
                    iterateNodes(e, obj);
                }
                array.add(obj);
            }
            json.put(nodeName, array);
        } else {
            //该元素同级下第一次遍历
            //获取该元素下所有子元素
            List<Element> listElement = node.elements();
            if(listElement.isEmpty()){
                //该元素无子元素，获取元素的值
                String nodeValue = node.getTextTrim();
                json.put(nodeName, nodeValue);
            } else {
                //有子节点，新建一个JSONObject来存储该节点下子节点的值
                JSONObject obj = new JSONObject();
                //遍历所有一级子节点
                for(Element e:listElement){
                    //递归
                    iterateNodes(e,obj);
                }
                json.put(nodeName, obj);
            }
        }

    }


    public static void main(String[] args) {
       /* String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ROOT><MESSAGE><requestid>60</requestid><serialno>2456146</serialno><parkinglot></parkinglot><parkname>中心医院</parkname><plateno>浙G0EJ77</plateno><opertime>2019-01-17 11:07:28</opertime><roadBrakeNo>19</roadBrakeNo><picName>1中心医院20190117110729浙-G0EJ77</picName></MESSAGE><msgid>0D0117110728201901171129117</msgid></ROOT>";
        Map json = XmlUtil.xmlToMap(message);
        System.out.println(json.get("ROOT"));*/
        Map<String,Object> value = new HashMap<>();
        value.put("system_id",12001);
        value.put("system_pass","wewqwqaqaawww");
        value.put("service","QUERYPARK");
        value.put("parkname","");
        System.out.println(XmlUtil.jsonToXML(JSON.toJSONString(value)));
    }

}
