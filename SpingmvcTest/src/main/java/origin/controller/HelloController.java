package origin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*
 *处理器类 
 */
public class HelloController implements Controller{
//http://localhost/springmvc01/hello.go
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("success!");
		// 创建一个ModelAndView对象。
		/*
		 * ModelAndView 有两个构造器：
		 * 
		 * 1.ModelAndView(String viewName) viewName是视图名。 2.ModelAndView(String
		 * viewName,Map data), data 是处理结果。
		 */
		//viewName 会拼接jsp  访问   viewName.jsp
		return new ModelAndView("hello");
	}
}
