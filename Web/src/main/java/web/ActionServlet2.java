package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ActionServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1321524682165408916L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 分析请求路径,依据请求不同,调用不同的分支来处理.
		// 比如:http://ip:port/ ajax-day01/abc.do 后面的字符串
		String uri = request.getRequestURI();
		// System.out.println("uri"+uri);
		String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		System.out.println("action:" + action);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if ("/getFlights".equals(action)) {
			String flight = request.getParameter("flight");
			System.out.println("flight:" + flight);
			if ("CA1234".equals(flight)) {
				out.println("头等舱:2400<br/>商务舱:2200");
			}
			if ("MU1494".equals(flight)) {
				out.println("头等舱:1800<br/>商务舱:1600");
			}
		} else if ("/order".equals(action)) {
			String addr = request.getParameter("addr");
			System.out.println("addr===" + addr);
			out.println("保存成功");
		} else {
		}
	}
}