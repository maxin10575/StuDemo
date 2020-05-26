package web;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {
	/**
	 * ��ν�java����ת���� json�ַ���
	 * {"code":"000410","name":"������","price":10}
	 */
	public static void test1(){
		Stock s  = new Stock();
		s.setCode("000410");
		s.setName("������");
		s.setPrice(10);
		//ʹ��json�ٷ��ṩ�Ĺ��� json-lib ����java����ת����
		//json�ַ���.
		JSONObject jsonObj = JSONObject.fromObject(s);
		String jsonStr = jsonObj.toString();
		System.out.println(jsonStr);
	}
	
	//��ν����������ɵ�������߼���ת����json�ַ���.
	public static void test2(){
		List<Stock> stocks = new ArrayList<Stock>();
		for (int i = 0; i < 3; i++) {
			Stock s  = new Stock();
			s.setCode("000410");
			s.setName("������");
			s.setPrice(10+i);
			stocks.add(s);
		}
		//fromObject����Ҳ����ʹ��������Ϊ����.
		JSONArray jsonArr = JSONArray.fromObject(stocks);
		String jsonStr = jsonArr.toString();
		System.out.println(jsonStr);
	}
	
	
	
	public static void main(String[] args) {
		
		test1();
	}
	
}
