package cookieandsession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet_session extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String code = req.getParameter("code");
		String code = "11111";
		String code22 = "22222";
		HttpSession session = request.getSession();
		session.setAttribute("code",code);
		session.setAttribute("code22",code22);
	}
	
}
