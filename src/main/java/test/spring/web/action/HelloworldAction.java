package test.spring.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/helloworld")
public class HelloworldAction {
	
	@RequestMapping("test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write("TEST page");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("index")
	public String indexVm(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
}
