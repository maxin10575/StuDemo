package web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Test extends HttpServlet{

	@Override
	public void service(HttpServletRequest request , HttpServletResponse response) throws IOException{
		String a = request.getParameter("taskCode");
		System.out.println("taskCode===="+a);
		PrintWriter pw = response.getWriter();
		pw.print("11111111");
		pw.close();
	}
}
