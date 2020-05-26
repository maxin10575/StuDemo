package cookieandsession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexServlet_session extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				//获取session，此session是登录时服务器创建的，在此请求中浏览器
				//通过cookie发送了SID，服务器依据SID找到了那个session
			HttpSession session = request.getSession();
			String code = (String) session.getAttribute("code");
			String code22 = (String) session.getAttribute("code22");
			response.setContentType("text/html;utf-8");
			PrintWriter out = response.getWriter();
			out.println(code);
			out.println(code22);
			out.close();
	}
		
}
