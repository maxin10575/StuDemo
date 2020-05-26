package com.epoint.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.entity.CourseInfo;
import com.epoint.utils.JsonUtil;
import com.epoint.dao.*;

@WebServlet("/courseadd")
public class CourseAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CourseAdd() {
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

		// json-->java对象;
		String data = request.getParameter("data");
		CourseInfo c = JsonUtil.jsonToObject(data, CourseInfo.class);
		System.out.println(data);
		CourseDao dao = new CourseDao();
		int i = dao.saveCourse(c);
		if (i > 0) {
			response.getWriter().print("保存成功");
		} else {
			response.getWriter().print("保存失败");
		}

	}

}
