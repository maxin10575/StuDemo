package web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindDeptServlet extends HttpServlet {
	//tomcat����ʱ�Ѿ�������context����ʹ������ȡ��web.xml�еĲ���
	//�û����ʴ˲�ѯ����ʱ�Ϳ��Դ�context���ȡ����������ˡ�
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

			ServletContext context = getServletContext();
			String size = context.getInitParameter("size");
			System.out.println("��ҳ��ѯ����"+size);
			
			//ͳ����վ����
			Integer count = (Integer) context.getAttribute("count");
			context.setAttribute("count", ++count);
			System.out.println(count);
	}
	
}
