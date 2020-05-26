package demo;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5593402790477324447L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取请求路径
		String path = req.getServletPath();
		//根据规范处理路径
		if("/findCost.do".equals(path)) {
			findCost(req,res);
		} else if("/toAddCost.do".equals(path)) {
			toAddCost(req,res);
		} else if("/addCost.do".equals(path)) {
			addCost(req,res);
		} else if("/toUpadteCost.do".equals(path)) {		
			toUpdateCost(req,res);
		} else if("/updateCost.do".equals(path)) {
			updateCost(req,res);
		} else if("/toLogin.do".equals(path)) { 
			toLogin(req,res);
		} else if("/toIndex.do".equals(path)) {
			toIndex(req,res);
		} else if("/login.do".equals(path)) {
			login(req,res);
		} else if("/toRemoveCost.do".equals(path)) { 
			toRemoveCost(req,res);
		} else if("/logout.do".equals(path)) {
			logout(req,res);
		} else if("/createImg.do".equals(path)) {
			createImg(req,res);
		} else {	
			throw new RuntimeException ("查无此页");
		}
	}

	protected void findEmp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1.接收请求参数
		// 当前业务没有参数需要接收
		// 2.使用参数处理业务（查询）
//		EmpDao dao = new EmpDao();
//		List<Emp> list = dao.findAll();

		// 3.输出响应信息
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		// 在此开发模式下写css太麻烦了，所以写元素的属性代替。th是特殊的td，
		// 自带了居中，加粗的效果。
		out.println("<a href='add_emp.html'>增加</a>");
		out.println("<table width='40%' border='1' cellspacing='0'>");
		out.println("	<tr>");
		out.println("   <th>编号</th>");
		out.println("   <th>姓名</th>");
		out.println("   <th>职位</th>");
		out.println("   <th>薪资</th>");
		out.println("	</tr>");
//		if (list != null) {
//			for (Emp e : list) {
//				out.println("<tr>");
//				out.println("  <td>" + e.getEmpno() + "</td>");
//				out.println("  <td>" + e.getEname() + "</td>");
//				out.println("  <td>" + e.getJob() + "</td>");
//				out.println("  <td>" + e.getSal() + "</td>");
//				out.println("</tr>");
//			}
//		}
		out.println("</table>");
	}


	//生成验证码图片
	protected void createImg(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//生成验证码及图片
		Object[] objs = ImageUtil.createImage();
		//将验证码存入session
		String imgCode = (String)objs[0];
		HttpSession session = req.getSession();
		session.setAttribute("imgCode", imgCode);
		
		//将图片输出给浏览器
		BufferedImage img = (BufferedImage)objs[1];
		res.setContentType("image/png");
		//tomcat自动创建输出流，目标就是本次访问的那个浏览器
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
		
	}
	//退出
	protected void logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//销毁session，从而删除其内部数据
		req.getSession().invalidate();
		//重定向到登陆界面
		res.sendRedirect("toLogin.do");
	}
	
	protected void toRemoveCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
//		CostDao dao = new CostDao();
//		dao.remove(id);
		res.sendRedirect("findCost.do");
//		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);

	}
	protected void updateCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//获取请求参数	
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		String costId = req.getParameter("costId");
		//保存数据
/*		Cost c = new Cost();
		c.setName(name);
		c.setCostType(costType);
		c.setDescr(descr);
		if(costId !=null && !costId.equals("")){
			c.setCostId(Integer.parseInt(costId));
		}
		if(baseDuration !=null && !baseDuration.equals("")) {
			c.setBaseDuration(Integer.parseInt(baseDuration));
		}
		if(baseCost !=null && !baseCost.equals("")) {
			c.setBaseCost(new Double(baseCost));
		}
		if(unitCost != null && !unitCost.equals("")) {
			c.setUnitCost(Double.parseDouble(unitCost));
		}
		CostDao dao = new CostDao();
		dao.update(c);*/
		
		res.sendRedirect("findCost.do");
	}
	
	//登陆，检查账号密码是否正确
	protected void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//接收表单数据
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		//先验证验证码
		HttpSession session = req.getSession();
		String imgCode = (String)session.getAttribute("imgCode");
		if(code == null || code.equals("") || !code.equalsIgnoreCase(imgCode)) {
			req.setAttribute("error", "验证码错误");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
			return;
		}
	/*	//验证账号密码
		AdminDao dao = new AdminDao();
		Admin a = dao.findByCode(adminCode);*/
	/*	if(a == null) {
			//账号不存在，或者账号错误
			//转发回登陆页面，并给予提示
			req.setAttribute("error", "账号错误");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else if(!a.getPassword().equals(password)){
			//账号存在  但是密码不正确，转发回登陆页面，给予提示 
			req.setAttribute("error", "密码错误");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else {
			//将账号存入cookie
			Cookie c = new Cookie("adminCode",adminCode);
			res.addCookie(c);*/
			//也可以将账号存入session
			
			//将值存入session
			session.setAttribute("adminCode", adminCode);
//			HttpSession session = req.getSession();
			//正确 验证通过  重定向到首页
			res.sendRedirect("toIndex.do");
		}

	//打开登陆页面
	protected void toLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);	
	}
	//打开首页
	/*protected void toIndex(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}*/
	protected void toIndex(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}
	
	//修改按钮
	protected void toUpdateCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//获取要修改的数据的id
		String id = req.getParameter("id");
		//查询要修该的数据
/*		CostDao dao = new CostDao();
		Cost cost = dao.findById(Integer.parseInt(id));
		//绑定数据
		req.setAttribute("cost", cost);*/
		//转发到jsp上
		req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, res);;
		
		
		//重定向到findCost.do
//		res.sendRedirect("findCost.do");
	}
	//增加资费
	protected void addCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//获取请求参数(保存的数据)
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		
		//封装然后保存这些数据
/*		Cost c = new Cost();
		c.setName(name);
		c.setCostType(costType);
		c.setDescr(descr);
		if(baseDuration !=null && !baseDuration.equals("")) {
			c.setBaseDuration(Integer.parseInt(baseDuration));
		}
		if(baseCost !=null && !baseCost.equals("")) {
			c.setBaseCost(new Double(baseCost));
		}
		if(unitCost != null && !unitCost.equals("")) {
			c.setUnitCost(Double.parseDouble(unitCost));
		}
		CostDao dao = new CostDao();
		dao.save(c);*/
		
		//重定向到查询
		//当前：/netctoss/addCost.do
		//目标：/netctoss/findCost.do
		res.sendRedirect("findCost.do");	
	}
	//打开资费增加页面
	protected void toAddCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//转发到jsp
		//相对路径参考资费查询
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}
	//查询资费
	protected void findCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//查询所有的资费
/*		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();*/
		//将其转发给JSP
//		req.setAttribute("costs", list);
		//当前:/netctoss/findCost.do
		//目标:/netctoss/WEB-INF/cost/find.jsp
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}
}














































































