package test.spring.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.common.ResponseUtils;
import test.spring.exception.BusinessException;
import test.spring.model.Blog;
import test.spring.service.BlogService;
import test.spring.web.WebUtils;

@Controller
@RequestMapping("/service/blog")
public class BlogAction extends AbstractAction {

	private static final Logger logger = Logger.getLogger(BlogAction.class);

	@Resource
	private BlogService blogService;

	@RequestMapping("saveBlog")
	public void addBlog(HttpServletRequest request, HttpServletResponse response, Blog blog) throws BusinessException {
		logger.info(blog.toJson());
		long loginUserId = WebUtils.getLoginUserId(request);
		if (blog.getId() > 0) {
			blogService.updateBlog(loginUserId, blog);
		} else {
			blogService.addBlog(loginUserId, blog);
		}
		ResponseUtils.writeSuccessResponse(request, response, blog.getId());
	}
}
