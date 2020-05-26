package web;

import java.io.IOException;

import javax.servlet.*;

public class MyFilter implements Filter {

	// �ر�tomcatʱ�����Զ����ô˷�����
	// ������Filter
	public void destroy() {
		System.out.println("����LogFilter");
	}

	// tomcat�ڴ�������ǰ�Զ����ô˷�����
	// ����request��response���롣
	// ʵ����tomcat�ǽ�����Ĵ���Ȩ��ȫ
	// ����������������������������������
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("��ǰ�����־");
		// 1.�����ô˷��������������
		// ������һ��������(Filter/Servlet)��
		// 2.�������ô˷��������������
		chain.doFilter(req, res);
		System.out.println("�ں������־");
	}

	// tomcat������ʱ���Զ�ʵ����Filter��
	// ���Զ�������init()��ʼ��Filter��
	// tomcat�ڴ���Filterǰ�����������1��
	// config���󣬲����ô˶����ȡweb.xml
	// ��Ԥ�õĲ�����Ȼ���ڳ�ʼ��Filterʱ
	// ����config�����롣
	public void init(FilterConfig cfg) throws ServletException {
		System.out.println("��ʼ��LogFilter");
		System.out.println(cfg.getInitParameter("city"));
	}




}
