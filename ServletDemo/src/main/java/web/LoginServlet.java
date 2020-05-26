package web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	//tomcat在创建此Servlet前会给它单独创建一个config对象，该对象
	//只给此servlet自己使用，其他servlet无法访问。并且在调用此servlet
	//的init（）方法时，将这个config对象传入。config被tomcat创建后
	//已经自动读取了web.xml中的参数
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String maxOnline = config.getInitParameter("maxOnline");
		System.out.println(maxOnline);
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			//1.接收传入的账号密码
			System.out.println("接收账号密码");
			//2.验证账号密码是否正确
			System.out.println("验证账号密码");
			//3.判断当前在线人数是否达上限
			ServletConfig config = getServletConfig();
			String maxOnline = config.getInitParameter("maxOnline");
			System.out.println(maxOnline);
			//4.进入游戏或排队
			System.out.println("进入游戏。。。");
		
			//统计网站流量
//			ctx.setAttribute("count", 3);
			ServletContext ctx = getServletContext();
			Integer count = (Integer) ctx.getAttribute("count");
			ctx.setAttribute("count", ++count);
			System.out.println(count);
	}
		
}
