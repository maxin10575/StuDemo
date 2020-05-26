package cookieandsession;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

public class IndexServlet_cookie extends HttpServlet {

	// 1.ģ����ҳ���ܡ�
	// 2.�ڴ�֮ǰ�û�һ����¼����
	// 3.������Ѿ�������cookie�����˺���Ϣ��
	// 4.��������ʱ�����ʱ���Զ�����cookie��
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ���𱾴�������������
		// ���������cookie��
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			for (Cookie cookie : cookies) {
				out.println("<p>");
				out.println(cookie.getName() + ":" + URLDecoder.decode(cookie.getValue(), "utf-8"));
				out.println("</p>");
			}
			out.close();
		}
	}
}
