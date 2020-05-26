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

	// 1.模拟登录验证功能。
	// 2.接收用户输入的账号。
	// 3.将其存入cookie。
	// 4.将cookie发送给浏览器。
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//		String code = req.getParameter("code");
		String code = "123";
		// 给发起本次请求的浏览器创建cookie。
		// 一个cookie只能存一份数据。
		// 并且只能保存字符串。
		Cookie c1 = new Cookie("code", code);
		// 设置cookie的生存时间：
		// 1)cookie默认存到浏览器内存中，
		// 当关闭浏览器时内存释放cookie消失。
		// 2)可以修改cookie的生存时间，
		// 一旦修改后cookie将存储在客户机的
		// 硬盘上，直到超时浏览器将它自动删除。
		c1.setMaxAge(60000);
		// 设置cookie的有效路径，cookie将在此
		// 路径以及它的下级路径下都有效。
		c1.setPath("/jsp3cookie");
		// 将cookie添加到response内，在响应时
		// 服务器会自动将cookie发送给浏览器。
		response.addCookie(c1);
		// 再创建一个cookie，保存中文。
		// 保存前必须对中文转码(ASKII)。
		Cookie c2 = new Cookie("city", URLEncoder.encode("北京", "utf-8"));
		response.addCookie(c2);
	}

}
