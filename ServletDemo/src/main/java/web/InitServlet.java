package web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/*
 * һ��web��Ŀ�ж���һ��InitServlet,����������tomcat
 * ����ʱ����ʼ��һЩ����
 * 
 * */
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7180934712733410714L;

	@Override
	public void init() throws ServletException {
		System.out.println("InitServlet===tomcat����ʱ����ʼ��һЩ����......");
		super.init();
		//��context�д�һ������count=0,������servletʹ��
		ServletContext ctx = getServletContext();
		ctx.setAttribute("count", 3);
	}

}
