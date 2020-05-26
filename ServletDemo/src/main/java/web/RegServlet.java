package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875946069945899429L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 获取请求数据
		String code = req.getParameter("code");
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		String[] interests = req.getParameterValues("interest");
		// 处理请求乱码
		// byte[] bs = code.getBytes("iso8859-1");
		// code = new String(bs,"utf-8");
		// 处理这些数据
		System.out.println(code);
		System.out.println(pwd);
		System.out.println(sex);
		if (interests != null) {
			for (String i : interests) {
				System.out.println(i);
			}
		}
		// 输出响应信息
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<p>注册成功</p>");
		out.close();

	}
}