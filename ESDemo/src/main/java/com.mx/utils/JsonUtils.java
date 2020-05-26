package com.mx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mx.model.User;
import com.sun.corba.se.impl.orbutil.ObjectWriter;

//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.ObjectWriter;


public class JsonUtils {
	
//	public static ObjectMapper mapper = new ObjectMapper();
	
	public static String toJson(Object o) {
		try {
//			ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
//			return writer.writeValueAsString(o);
			JSONObject json = (JSONObject) JSONObject.toJSON(o);
			return json.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			return "转换发生异常";
		}
	}
	
	public static void main(String[] args) {
		User user = new User(11, "kevin", 123, false);
		System.out.println( JsonUtils.toJson(user) );
	}
}
