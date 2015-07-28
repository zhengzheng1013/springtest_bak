package test.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import test.spring.dao.BlogDAO;
import test.spring.exception.BusinessException;
import test.spring.model.Blog;
import test.spring.web.ErrorCode;

@Service
public class BlogService {

	@Resource
	private BlogDAO blogDAO;
	
	public void addBlog(long userId, Blog blog) {
		blog.setUserId(userId);
		blogDAO.addBlog(blog);
	}
	
	public Blog getBlog(long blogId) {
		return blogDAO.getBlog(blogId);
	}
	
	public List<Blog> getBlogList(long userId) {
		return blogDAO.getBlogList(userId);
	}
	
	public void updateBlog(long userId, Blog blog) throws BusinessException {
		Blog oldBlog = getBlog(blog.getId());
		if(oldBlog == null) {
			throw new BusinessException(ErrorCode.BLOG_NOT_EXISTS);
		}
		if(oldBlog.getUserId() != userId) {
			throw new BusinessException(ErrorCode.USER_NOT_BLOG_OWNER);
		}
		blog.setUserId(userId);
		blogDAO.updateBlog(blog);
	}
}
