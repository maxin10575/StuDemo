package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.dao.CourseDao;

@WebServlet("/coursedelete")
public class CourseDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CourseDelete() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String ids = request.getParameter("data");
		// System.out.println("data==="+ids);
		CourseDao dao = new CourseDao();
		int i = dao.delete(ids);
		if (i > 0) {
			response.getWriter().print("删除成功");
		} else {
			response.getWriter().print("删除失败");
		}
	}

}
