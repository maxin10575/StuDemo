package demo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoFilter implements Filter {

	public void destroy() {
		System.out.println("destroy~~~~~~~~~~~");
	}
	//tomcat启动时自动调用此方法，传入的是HttpServletRequest和HttpServletResponse
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		//将不需要过滤的路径排除掉
		//只有这三个路径可以继续访问
		String[] paths = new String[] {
			"/toLogin.do","/createImg.do","/login.do"
		};
		//当前的路径
		String p = request.getServletPath();
		for(String path : paths) {
			if(p.equals(path)) {
				chain.doFilter(req, res);
				return;
			}
		}
		
		//从session中获取账号
		
		String name = req.getParameter("adminCode");
		HttpSession session = request.getSession();
		Object adminCode = session.getAttribute("adminCode");
		//根据账号判断用户是否登陆
		if(adminCode == null) {
			//没登陆,重定向到登陆界面
			response.sendRedirect("/netctoss/toLogin.do");
		}else {
			//登陆了，请求继续执行
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		System.out.println("init~~~~~~~~~~~~");
	}
}
