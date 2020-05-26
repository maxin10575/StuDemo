package control;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HelloController {

	@RequestMapping("/pages/hello.do")
	public String hello() {
		System.out.println("hello()");
		return "hello";
	}

	@RequestMapping("/pages/toLogin.do")
	public String toLogin() {
		System.out.println("toLogin()");
		return "login";
	}
//    获取页面数据方法
	@RequestMapping("/pages/login.do")
	public String checkLogin1(HttpServletRequest request) {
		System.out.println("checkLogin1()");
		String adminCode = request.getParameter("adminCode");
		String pwd = request.getParameter("pwd");
		System.out.println("adminCode:" + adminCode + "  pwd:" + pwd);
		return "/pages/index";
	}

	@RequestMapping("/pages/login2.do")
	public String checkLogin2(String adminCode, String pwd) {
		System.out.println("checkLogin2()");
		System.out.println("adminCode:" + adminCode + "  pwd" + pwd);
		return "index";
	}

	@RequestMapping("/pages/login21.do")
	public String checkLogin21(@RequestParam("adminCode") String adminCode, @RequestParam("pwd") String password) {
		System.out.println("checkLogin21()");
		System.out.println("adminCode:" + adminCode + " passwprd" + password);
		return "index";
	}

	@RequestMapping("/pages/login3.do")
	public String checkLogin3(AdminParam ap) {
		System.out.println("checkLogin3()");
		System.out.println("adminCode" + ap.getAdminCode());
		System.out.println("password" + ap.getPwd());
		return "index";
	}


	//向页面返回数据
	@RequestMapping("/pages/login4.do")
	public String checkLogin4(AdminParam ap, HttpServletRequest request) {
		System.out.println("checkLogin4()");
		System.out.println(" ap.getAdminCode()==="+ ap.getAdminCode());
		request.setAttribute("adminCode", ap.getAdminCode());
		return "/pages/index";
	}


	@RequestMapping("/login5.do")
	public String checkLogin5(AdminParam ap, HttpSession session) {
		System.out.println("checkLogin5()");
		session.setAttribute("adminCode", ap.getAdminCode());
		return "index";
	}

	@RequestMapping("/pages/login6.do")
	public String checkLogin6(AdminParam ap, ModelMap modelMap) {
		System.out.println("checkLogin6()");

		modelMap.addAttribute("adminCode", ap.getAdminCode());
		return "index";
	}


	@RequestMapping("/login7.do")
	public ModelAndView checkLogin7(AdminParam ap) {
		System.out.println("checkLogin7()");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminCode", ap.getAdminCode());
		ModelAndView modelAndView = new ModelAndView("index", map);
		return modelAndView;
	}


//	重定向
	@RequestMapping("/login8.do")
	public String checkLogin8() {
		System.out.println("checkLogin8()");
		return "redirect:/pages/toIndex.do";
	}


	@RequestMapping("/login9.do")
	public ModelAndView checkLogin9() {
		System.out.println("checkLogin9()");
		RedirectView redirectView = new RedirectView("toIndex.do");
		return new ModelAndView(redirectView);
	}

	@RequestMapping("/toIndex.do")
	public String toIndex() {
		return "index";
	}

}
