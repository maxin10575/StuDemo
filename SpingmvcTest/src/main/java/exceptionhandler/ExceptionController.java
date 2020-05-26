package exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionController {
	@ExceptionHandler
	/*
	 * �ڶ��ַ�ʽ��ʹ��@ExceptionHandler ����һ���쳣������,�������������������׳����쳣. ex:�����������׳����쳣.
	 */
	public String handler11(Exception ex, HttpServletRequest request) {
		System.out.println("handler()");
		// �����쳣����,�ֱ�����ͬ�Ĵ���
		if (ex instanceof NumberFormatException) {
			request.setAttribute("errorMsg", "��������ȷ������@ExceptionHandler");
			return "/pages/errors/error2";
		} else if (ex instanceof StringIndexOutOfBoundsException) {
			request.setAttribute("errorMsg", "����Խ��");
			return "/pages/errors/error2";
		} else {
			// �����쳣,��ʾ�û��Ժ�����.
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