package web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	//tomcat�ڴ�����Servletǰ�������������һ��config���󣬸ö���
	//ֻ����servlet�Լ�ʹ�ã�����servlet�޷����ʡ������ڵ��ô�servlet
	//��init��������ʱ�������config�����롣config��tomcat������
	//�Ѿ��Զ���ȡ��web.xml�еĲ���
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String maxOnline = config.getInitParameter("maxOnline");
		System.out.println(maxOnline);
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			//1.���մ�����˺�����
			System.out.println("�����˺�����");
			//2.��֤�˺������Ƿ���ȷ
			System.out.println("��֤�˺�����");
			//3.�жϵ�ǰ���������Ƿ������
			ServletConfig config = getServletConfig();
			String maxOnline = config.getInitParameter("maxOnline");
			System.out.println(maxOnline);
			//4.������Ϸ���Ŷ�
			System.out.println("������Ϸ������");
		
			//ͳ����վ����
//			ctx.setAttribute("count", 3);
			ServletContext ctx = getServletContext();
			Integer count = (Integer) ctx.getAttribute("count");
			ctx.setAttribute("count", ++count);
			System.out.println(count);
	}
		
}
