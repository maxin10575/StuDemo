package jspdemo.web;

import jspdemo.dao.EmpDao;
import jspdemo.entity.Emp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindEmpServlet extends HttpServlet {

	private static final long serialVersionUID = 845938879245201483L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("start.......");
			//查询所有的员工
			EmpDao dao = new EmpDao();
			List<Emp> list = dao.findAll();
			//转发给JSP
			//将数据绑定到request上
			req.setAttribute("emp",list);
			//将请求及数据转发给JSP
			//当前：/jsp1/findEmp
			//目标：/jsp1/emps.jsp
			req.getRequestDispatcher("emps.jsp").forward(req, res);
			
	}

	
}
