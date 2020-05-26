package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = -1321524682165408916L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
	
		PrintWriter out = response.getWriter();

		if ("/check_uname".equals(action)) {
			String username = request.getParameter("username");
//			String a = new String(username.getBytes("utf8"), "gbk"); 
			username = new String(username.getBytes("iso-8859-1"), "utf8"); 
			System.out.println("username===" + username);
			if ("张三".equals(username)) {
				out.println("用户名被占用");
			} else {
				out.println("可以使用");
			}
		} else {

			String rando = request.getParameter("random");
			out.println(rando);
		}
	}

}
