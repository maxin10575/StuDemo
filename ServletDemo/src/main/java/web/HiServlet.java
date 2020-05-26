package web;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HiServlet extends HttpServlet {

	private static final long serialVersionUID = -7672827800031746497L;

	//1.Ĭ���״η���ʱʵ����
	//2.�޸����ú�������tomcatʱʵ����
	public HiServlet(){
		System.out.println("shilihua Servlet");
	}
	//��ʵ����Servlet����tomcat�Զ�����
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("InitHiServlet");
	}
	//ÿ�η��ʶ����Ե���
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			System.out.println("StartHiServlet");
	}
	
	//�����ر�tomcatʱ��������
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroyServlet....");
	}
}
