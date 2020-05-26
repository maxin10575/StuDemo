package exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionController {
	@ExceptionHandler
	/*
	 * 第二种方式：使用@ExceptionHandler 这是一个异常处理方法,用来处理其它方法所抛出的异常. ex:其它方法所抛出的异常.
	 */
	public String handler11(Exception ex, HttpServletRequest request) {
		System.out.println("handler()");
		// 依据异常类型,分别做不同的处理
		if (ex instanceof NumberFormatException) {
			request.setAttribute("errorMsg", "请输入正确的数字@ExceptionHandler");
			return "/pages/errors/error2";
		} else if (ex instanceof StringIndexOutOfBoundsException) {
			request.setAttribute("errorMsg", "数组越界");
			return "/pages/errors/error2";
		} else {
			// 其它异常,提示用户稍后重试.
			return "/pages/errors/error";
		}
	}

	@RequestMapping("/hello.do")
	public String hello1(HttpServletRequest request) {
		System.out.println("hello1()");
		String number = request.getParameter("number");
		Integer.parseInt(number);
		return "hello";
	}
}