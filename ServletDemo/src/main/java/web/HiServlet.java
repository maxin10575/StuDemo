package web;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HiServlet extends HttpServlet {

	private static final long serialVersionUID = -7672827800031746497L;

	//1.默认首次访问时实例化
	//2.修改配置后再启动tomcat时实例化
	public HiServlet(){
		System.out.println("shilihua Servlet");
	}
	//在实例化Servlet后由tomcat自动调用
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("InitHiServlet");
	}
	//每次访问都可以调用
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			System.out.println("StartHiServlet");
	}
	
	//正常关闭tomcat时调用销毁
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroyServlet....");
	}
}
