package course.action;

import com.epoint.dao.CourseDao;
import com.epoint.entity.CourseInfo;
import com.epoint.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/courseedit")
public class CourseEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CourseEdit() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method= request.getParameter("method");
		if(method.equals("queryById")){
			queryById(request,response);
		}else{
			edit(request,response);
		}
	}
		
	private void  queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("data")!=null?request.getParameter("data").trim():"";
		System.out.println("id===="+id);
		CourseDao dao = new CourseDao();
		CourseInfo c =dao.queryById(id);
		String json = JsonUtil.objectToJson(c);
		response.getWriter().print(json);		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data=request.getParameter("data");
		System.out.println("目前实体JSON===="+data);
		CourseInfo c =JsonUtil.jsonToObject(data, CourseInfo.class);
		System.out.println("目前实体===="+c);
		System.out.println("目前实体Housrs===="+c.getTotalhours());
		CourseDao dao = new CourseDao();
		int i =dao.edit(c);
		if(i>0){
			response.getWriter().print("修改成功");
			
		}else{
			response.getWriter().print("error");
		}
		
	}
		
	}
	


