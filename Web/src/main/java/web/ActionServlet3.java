package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ActionServlet3 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1321524682165408916L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取请求资源路径
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if (path.equals("/get_text")) {// get请求
			out.println("来自星星的你");
		} else if (path.equals("/post_text")) {// post请求
			String name = request.getParameter("uname");
			System.out.println(name);
			out.println("又来了一次的" + name);
		} else if (path.equals("/check_name")) {// 检查用户名
			String name = request.getParameter("uname");
			// 模拟一个耗时的操作
			if (1 == 1) {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(name);
			if ("Luffy".equals(name)) {
				out.println("该用户名不可用");
			} else {
				out.println("可以使用！");
			}
		} else if (path.equals("/getCity")) {// 城市列表联动
			String name = request.getParameter("name");
			if ("bj".equals(name)) {
				out.println("朝阳,cy;东城,dc");
			} else if ("sh".equals(name)) {
				out.println("静安,ja;黄浦,hp;浦东新,pdx");
			} else {
				out.println("白云,by;番禺,py");
			}
		}
		out.close();
	}
}