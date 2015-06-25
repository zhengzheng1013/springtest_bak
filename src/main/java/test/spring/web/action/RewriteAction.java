package test.spring.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/programs/view")
public class RewriteAction extends AbstractAction {

	@RequestMapping("/{id}/rewrite")
	public void rewrite(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			response.getWriter().write(id+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
