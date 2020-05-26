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
		// ��ȡ������Դ·��
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if (path.equals("/get_text")) {// get����
			out.println("�������ǵ���");
		} else if (path.equals("/post_text")) {// post����
			String name = request.getParameter("uname");
			System.out.println(name);
			out.println("������һ�ε�" + name);
		} else if (path.equals("/check_name")) {// ����û���
			String name = request.getParameter("uname");
			// ģ��һ����ʱ�Ĳ���
			if (1 == 1) {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(name);
			if ("Luffy".equals(name)) {
				out.println("���û���������");
			} else {
				out.println("����ʹ�ã�");
			}
		} else if (path.equals("/getCity")) {// �����б�����
			String name = request.getParameter("name");
			if ("bj".equals(name)) {
				out.println("����,cy;����,dc");
			} else if ("sh".equals(name)) {
				out.println("����,ja;����,hp;�ֶ���,pdx");
			} else {
				out.println("����,by;��خ,py");
			}
		}
		out.close();
	}
}