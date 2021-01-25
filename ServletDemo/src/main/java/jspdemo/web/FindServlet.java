package jspdemo.web;


import jspdemo.entity.Course;
import jspdemo.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6801099384348049469L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			//ģ���ѯѧ��
			Student stu =new Student();
			stu.setName("zhangsan");
			stu.setAge(23);
			stu.setSex("M");
			stu.setInterests(new String[]{"����","����","����"});
			Course c = new Course();
			c.setId(1);
			c.setName("java");
			c.setDays(80);
			stu.setCourse(c);
			System.out.println("111");
			//ת��
			req.setAttribute("stu",stu);
			req.getRequestDispatcher("find.jsp").forward(req, res);
	}
		
}
