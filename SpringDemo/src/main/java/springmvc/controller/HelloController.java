package springmvc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *�������� 
 */
public class HelloController implements Controller{
//http://localhost/springmvc01/hello.go
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("success!");
		// ����һ��ModelAndView����
		/*
		 * ModelAndView ��������������
		 * 
		 * 1.ModelAndView(String viewName) viewName����ͼ���� 2.ModelAndView(String
		 * viewName,Map data), data �Ǵ�������
		 */
		//viewName ��ƴ��jsp  ����   viewName.jsp
		return new ModelAndView("hello");
	}
}
