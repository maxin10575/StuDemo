package cookieandsession;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class LoginServlet_cookie extends HttpServlet {
	private static final long serialVersionUID = -4563094501016356110L;

	// 1.ģ���¼��֤���ܡ�
	// 2.�����û�������˺š�
	// 3.�������cookie��
	// 4.��cookie���͸��������
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//		String code = req.getParameter("code");
		String code = "123";
		// �����𱾴���������������cookie��
		// һ��cookieֻ�ܴ�һ�����ݡ�
		// ����ֻ�ܱ����ַ�����
		Cookie c1 = new Cookie("code", code);
		// ����cookie������ʱ�䣺
		// 1)cookieĬ�ϴ浽������ڴ��У�
		// ���ر������ʱ�ڴ��ͷ�cookie��ʧ��
		// 2)�����޸�cookie������ʱ�䣬
		// һ���޸ĺ�cookie���洢�ڿͻ�����
		// Ӳ���ϣ�ֱ����ʱ����������Զ�ɾ����
		c1.setMaxAge(60000);
		// ����cookie����Ч·����cookie���ڴ�
		// ·���Լ������¼�·���¶���Ч��
		c1.setPath("/jsp3cookie");
		// ��cookie��ӵ�response�ڣ�����Ӧʱ
		// ���������Զ���cookie���͸��������
		response.addCookie(c1);
		// �ٴ���һ��cookie���������ġ�
		// ����ǰ���������ת��(ASKII)��
		Cookie c2 = new Cookie("city", URLEncoder.encode("����", "utf-8"));
		response.addCookie(c2);
	}

}
