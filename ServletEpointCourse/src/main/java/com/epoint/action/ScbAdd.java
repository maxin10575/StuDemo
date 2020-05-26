package com.epoint.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.dao.ScbDao;
import com.epoint.entity.SubscribedInfo;
import com.epoint.utils.JsonUtil;

/**
 * Servlet implementation class ScbAdd
 */
@WebServlet("/scbadd")
public class ScbAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScbAdd() {
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
		response.setCharacterEncoding("utf-8");
	//  json-->java对象;
			String data = request.getParameter("data");
			SubscribedInfo s = JsonUtil.jsonToObject(data, SubscribedInfo.class);
			System.out.println(data);
						
			ScbDao dao = new ScbDao();
			//更新第一张表
		
			int i = dao.saveScb(s);
			if(i>0){
				response.getWriter().print("保存成功");			
			}else{
				response.getWriter().print("保存失败");
			}
	}

}
