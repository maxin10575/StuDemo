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
	//tomcat����ʱ�Զ����ô˷������������HttpServletRequest��HttpServletResponse
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		//������Ҫ���˵�·���ų���
		//ֻ��������·�����Լ�������
		String[] paths = new String[] {
			"/toLogin.do","/createImg.do","/login.do"
		};
		//��ǰ��·��
		String p = request.getServletPath();
		for(String path : paths) {
			if(p.equals(path)) {
				chain.doFilter(req, res);
				return;
			}
		}
		
		//��session�л�ȡ�˺�
		
		String name = req.getParameter("adminCode");
		HttpSession session = request.getSession();
		Object adminCode = session.getAttribute("adminCode");
		//�����˺��ж��û��Ƿ��½
		if(adminCode == null) {
			//û��½,�ض��򵽵�½����
			response.sendRedirect("/netctoss/toLogin.do");
		}else {
			//��½�ˣ��������ִ��
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		System.out.println("init~~~~~~~~~~~~");
	}
}
