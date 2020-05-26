package web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/*
 * 一般web项目中都有一个InitServlet,它仅仅是在tomcat
 * 启动时，初始化一些数据
 * 
 * */
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7180934712733410714L;

	@Override
	public void init() throws ServletException {
		System.out.println("InitServlet===tomcat启动时，初始化一些数据......");
		super.init();
		//向context中存一个变量count=0,给其他servlet使用
		ServletContext ctx = getServletContext();
		ctx.setAttribute("count", 3);
	}

}
