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
		//��ȡ����·��
		String path = req.getServletPath();
		//���ݹ淶����·��
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
			throw new RuntimeException ("���޴�ҳ");
		}
	}

	protected void findEmp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1.�����������
		// ��ǰҵ��û�в�����Ҫ����
		// 2.ʹ�ò�������ҵ�񣨲�ѯ��
//		EmpDao dao = new EmpDao();
//		List<Emp> list = dao.findAll();

		// 3.�����Ӧ��Ϣ
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		// �ڴ˿���ģʽ��дcss̫�鷳�ˣ�����дԪ�ص����Դ��档th�������td��
		// �Դ��˾��У��Ӵֵ�Ч����
		out.println("<a href='add_emp.html'>����</a>");
		out.println("<table width='40%' border='1' cellspacing='0'>");
		out.println("	<tr>");
		out.println("   <th>���</th>");
		out.println("   <th>����</th>");
		out.println("   <th>ְλ</th>");
		out.println("   <th>н��</th>");
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


	//������֤��ͼƬ
	protected void createImg(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//������֤�뼰ͼƬ
		Object[] objs = ImageUtil.createImage();
		//����֤�����session
		String imgCode = (String)objs[0];
		HttpSession session = req.getSession();
		session.setAttribute("imgCode", imgCode);
		
		//��ͼƬ����������
		BufferedImage img = (BufferedImage)objs[1];
		res.setContentType("image/png");
		//tomcat�Զ������������Ŀ����Ǳ��η��ʵ��Ǹ������
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
		
	}
	//�˳�
	protected void logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//����session���Ӷ�ɾ�����ڲ�����
		req.getSession().invalidate();
		//�ض��򵽵�½����
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
		//��ȡ�������	
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		String costId = req.getParameter("costId");
		//��������
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
	
	//��½������˺������Ƿ���ȷ
	protected void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//���ձ�����
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		//����֤��֤��
		HttpSession session = req.getSession();
		String imgCode = (String)session.getAttribute("imgCode");
		if(code == null || code.equals("") || !code.equalsIgnoreCase(imgCode)) {
			req.setAttribute("error", "��֤�����");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
			return;
		}
	/*	//��֤�˺�����
		AdminDao dao = new AdminDao();
		Admin a = dao.findByCode(adminCode);*/
	/*	if(a == null) {
			//�˺Ų����ڣ������˺Ŵ���
			//ת���ص�½ҳ�棬��������ʾ
			req.setAttribute("error", "�˺Ŵ���");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else if(!a.getPassword().equals(password)){
			//�˺Ŵ���  �������벻��ȷ��ת���ص�½ҳ�棬������ʾ 
			req.setAttribute("error", "�������");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else {
			//���˺Ŵ���cookie
			Cookie c = new Cookie("adminCode",adminCode);
			res.addCookie(c);*/
			//Ҳ���Խ��˺Ŵ���session
			
			//��ֵ����session
			session.setAttribute("adminCode", adminCode);
//			HttpSession session = req.getSession();
			//��ȷ ��֤ͨ��  �ض�����ҳ
			res.sendRedirect("toIndex.do");
		}

	//�򿪵�½ҳ��
	protected void toLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);	
	}
	//����ҳ
	/*protected void toIndex(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}*/
	protected void toIndex(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}
	
	//�޸İ�ť
	protected void toUpdateCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//��ȡҪ�޸ĵ����ݵ�id
		String id = req.getParameter("id");
		//��ѯҪ�޸õ�����
/*		CostDao dao = new CostDao();
		Cost cost = dao.findById(Integer.parseInt(id));
		//������
		req.setAttribute("cost", cost);*/
		//ת����jsp��
		req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, res);;
		
		
		//�ض���findCost.do
//		res.sendRedirect("findCost.do");
	}
	//�����ʷ�
	protected void addCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//��ȡ�������(���������)
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		
		//��װȻ�󱣴���Щ����
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
		
		//�ض��򵽲�ѯ
		//��ǰ��/netctoss/addCost.do
		//Ŀ�꣺/netctoss/findCost.do
		res.sendRedirect("findCost.do");	
	}
	//���ʷ�����ҳ��
	protected void toAddCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//ת����jsp
		//���·���ο��ʷѲ�ѯ
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}
	//��ѯ�ʷ�
	protected void findCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ѯ���е��ʷ�
/*		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();*/
		//����ת����JSP
//		req.setAttribute("costs", list);
		//��ǰ:/netctoss/findCost.do
		//Ŀ��:/netctoss/WEB-INF/cost/find.jsp
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}
}














































































