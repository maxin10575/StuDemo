package web;


import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActionServlet4 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1321524682165408916L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if ("/quoto".equals(action)) {
			List<Stock> stocks = new ArrayList<Stock>();
			Random r = new Random();
			for (int i = 0; i < 8; i++) {
				Stock s = new Stock();
				s.setCode("000410" + r.nextInt(10));
				s.setName("1111" + r.nextInt(10));
				s.setPrice(r.nextInt(1000));
				stocks.add(s);
			}
			String jsonStr= JSON.toJSONString(stocks);//关键
			out.println(jsonStr);
		}
	}
}