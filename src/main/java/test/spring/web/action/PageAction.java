package test.spring.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.spring.model.User;
import test.spring.service.AccountService;
import test.spring.service.UserService;
import test.spring.web.WebUtils;

@Controller
@RequestMapping("/")
public class PageAction extends AbstractAction {
	
	@Resource
	private UserService userService;
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping({"/", "index"})
	public Object index(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = WebUtils.getCookie(request, "uid");
		if(cookie != null) {
			int userId = Integer.parseInt(cookie.getValue());
			User user = userService.getUser(userId);
			if(user == null) {
				WebUtils.deleteCookie(request, response, "uid");
				return "redirect:login.action";
			}
			
			double balance = accountService.getBalance(userId);
			Map<String, Object> model = new HashMap<String, Object>();
			model.putAll(user.toJSONObject());
			model.put("balance", balance);
			ModelAndView mv = new ModelAndView("index", model);
			return mv;
		}
		
		return "redirect:login.action";
	}
	
	@RequestMapping("register")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = WebUtils.getCookie(request, "uid");
		if(cookie != null) {
			return "redirect:index.action";
		}
		return "login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		WebUtils.deleteCookie(request, response, "uid");
		return "redirect:login.action";
	}
}
