package web;

import java.io.IOException;

import javax.servlet.*;

public class MyFilter implements Filter {

	// 关闭tomcat时它会自动调用此方法，
	// 来销毁Filter
	public void destroy() {
		System.out.println("销毁LogFilter");
	}

	// tomcat在处理请求前自动调用此方法，
	// 并将request、response传入。
	// 实际上tomcat是将请求的处理权完全
	// 交给了这个方法，由这个方法来处理请求。
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("在前面记日志");
		// 1.若调用此方法则请求继续，
		// 交给下一个处理者(Filter/Servlet)。
		// 2.若不调用此方法则请求结束。
		chain.doFilter(req, res);
		System.out.println("在后面记日志");
	}

	// tomcat在启动时会自动实例化Filter，
	// 并自动调用其init()初始化Filter。
	// tomcat在创建Filter前，会给它创建1个
	// config对象，并调用此对象读取web.xml
	// 中预置的参数，然后在初始化Filter时
	// 将此config对象传入。
	public void init(FilterConfig cfg) throws ServletException {
		System.out.println("初始化LogFilter");
		System.out.println(cfg.getInitParameter("city"));
	}




}
