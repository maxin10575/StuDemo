package com.epoint.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.epoint.dao.CourseDao;
import com.epoint.dao.ScbDao;
import com.epoint.entity.CourseInfo;
import com.epoint.entity.SubscribedInfo;



public class FlowNumber {
	public static String fetchFlowNumber(){
		CourseDao dao=new CourseDao();
		//修改list泛型 和 dao方法
		List<CourseInfo> list = dao.listAll();
		String flowNumber = "0001";
		if(list.size() >0){			
			String id = list.get(list.size()-1).getId();
		//修改substring参数	
			int beforeId = Integer.parseInt(id.substring(2));
			flowNumber= String.format("%04d", (beforeId+1));
		}else{
			return "KC0001";
		}
		return "KC"+flowNumber;
	}
	
	public static String subfetchFlowNumber(){
		ScbDao dao=new ScbDao();
		//修改list泛型 和 dao方法
    	List<SubscribedInfo> list=dao.listScbAll();
    	
    	String flowNumber = "0001";
    	
    	Date date = new Date();
    	SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMM");
    	String str = sdf.format(date);
 	
		if(list.size() > 0){
			/*获取最后一个记录的编号*/
			String id = list.get(list.size()-1).getSalaryid();
	//修改substring参数
			int beforeId = Integer.parseInt(id.substring(8));
			flowNumber= String.format("%04d", (beforeId+1));
		}
		else{
			return "XK"+str+"0001";
		}
		return "XK"+str+flowNumber;
	}
	
	public static void main(String[] args) {
		
		String s1 = FlowNumber.fetchFlowNumber();
		String s2 = FlowNumber.subfetchFlowNumber();
		System.out.println(s1);
		System.out.println(s2);
	}
}
 