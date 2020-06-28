package course.action;

import com.epoint.dao.ScbDao;
import com.epoint.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Servlet implementation class ScbList
 */
@WebServlet("/scblistaction")
public class ScbList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScbList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int index = Integer.parseInt(request.getParameter("pageIndex"));
		int size= Integer.parseInt(request.getParameter("pageSize"));
		String key = request.getParameter("key");
		 HashMap map = new HashMap();
		ScbDao dao = new ScbDao();
	
		if(key == null || key ==""){
			List dataAll =dao.listScbAll();
			int start = index * size ;
			int end = start + size;
			List data = new ArrayList();
	        for (int i = 0, l = dataAll.size(); i < l; i++)
	        {
	            Object record =  dataAll.get(i);
	            if (record == null) continue;
	            if (index * size <= i && i < (index+1)*size)
	            {
	                data.add(record);
	            }
	        }	       
	        map.put("data", data);
	        map.put("total", dataAll.size());	
			String json = JsonUtil.objectToJson(map);
			response.getWriter().print(json);
		}
		else if(key.matches("\\d+")){
			List list = dao.findByactualhours(key);
			System.out.println(list);
			if(list != null){
				map.put("data", list);
				String json = JsonUtil.objectToJson(map);
				response.getWriter().println(json);
					}				
			}else {	
			List list = dao.scbfindByCourseName(key);					
			System.out.println(list);
			if(list != null){
				map.put("data", list);
				String json = JsonUtil.objectToJson(map);
				response.getWriter().println(json);
			}			
		}
		
		}
	
	}