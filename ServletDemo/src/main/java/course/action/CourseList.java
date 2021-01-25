package course.action;


import course.dao.CourseDao;
import course.entity.CourseInfo;
import course.utils.JsonUtil;
import course.utils.Transform;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/courselistaction")
public class CourseList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CourseList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		int index = Integer.parseInt(request.getParameter("pageIndex"));
		System.out.println("index==="+index);
		int size = Integer.parseInt(request.getParameter("pageSize"));
		String key = request.getParameter("key");
		HashMap<String, Object> map = new HashMap<String, Object>();
		CourseDao dao = new CourseDao();

		if (key == null || key == "") {
			List<CourseInfo> dataAll = dao.listAll();
//			int start = index * size;
//			int end = start + size;
			List<Object> data = new ArrayList<Object>();
			for (int i = 0, l = dataAll.size(); i < l; i++) {
				Object record = dataAll.get(i);
				if (record == null)
					continue;
				if (index * size <= i && i < (index + 1) * size) {
					data.add(record);
				}
			}
			map.put("data", data);
			map.put("total", dataAll.size());
			String json = JsonUtil.objectToJson(map);
			System.out.println("json==="+json);
			response.getWriter().print(json);
		} else if (key.equals("C#") || key.equals("Java") || key.equals("C++")) {
			key = Transform.transfromNumber(key);
			List list = dao.findByType(key);
			System.out.println(list);
			if (list != null) {
				map.put("data", list);
				String json = JsonUtil.objectToJson(map);
				response.getWriter().println(json);
			}
		} else {
			List list = dao.findByCoursename(key);
			System.out.println(list);
			if (list != null) {
				map.put("data", list);
				String json = JsonUtil.objectToJson(map);
				response.getWriter().println(json);
			}
		}

	}

}