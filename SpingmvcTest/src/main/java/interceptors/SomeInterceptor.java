package interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SomeInterceptor implements HandlerInterceptor {

	/**
	 * ex: 处理器方法所抛出的异常。
	 */
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception ex)
			throws Exception {
		System.out.println("afterCompletion()");
	}

	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView mav)
			throws Exception {
		 // 从session当中获取特定的数据
//		  HttpSession session = req.getSession();
//	     Object obj = session.getAttribute("adminCode");
//		System.out.println("obj===="+obj);
		System.out.println("postHandle()");

	}

	/**
	 * arg2: 用来获得处理器方法描述的对象。
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse res, Object arg2) throws Exception {
		String adminCode = request.getParameter("adminCode");
		String pwd = request.getParameter("pwd");
		System.out.println("preHandle-adminCode===="+adminCode);
		System.out.println("preHandle-pwd===="+pwd);
		return true;
		//return false;
	}

}
