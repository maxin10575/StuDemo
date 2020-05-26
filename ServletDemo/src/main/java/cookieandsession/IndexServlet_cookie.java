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

	// 1.模拟主页功能。
	// 2.在此之前用户一定登录过。
	// 3.浏览器已经保存了cookie，含账号信息。
	// 4.浏览器访问本功能时会自动传入cookie。
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取发起本次请求的浏览器上
		// 保存的所有cookie。
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
