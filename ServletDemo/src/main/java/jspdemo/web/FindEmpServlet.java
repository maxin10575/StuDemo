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
			//��ѯ���е�Ա��
			EmpDao dao = new EmpDao();
			List<Emp> list = dao.findAll();
			//ת����JSP
			//�����ݰ󶨵�request��
			req.setAttribute("emp",list);
			//����������ת����JSP
			//��ǰ��/jsp1/findEmp
			//Ŀ�꣺/jsp1/emps.jsp
			req.getRequestDispatcher("emps.jsp").forward(req, res);
			
	}

	
}
