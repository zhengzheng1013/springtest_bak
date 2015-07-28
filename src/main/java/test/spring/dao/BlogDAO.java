package test.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import test.spring.model.Blog;

@Repository
public class BlogDAO extends AbstractDAO {

	public void addBlog(Blog blog) {
		sqlSession.insert("BlogDAO.addBlog", blog);
	}

	public List<Blog> getBlogList(long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return sqlSession.selectList("BlogDAO.getBlogList", params);
	}

	public Blog getBlog(long blogId) {
		return sqlSession.selectOne("BlogDAO.getBlog", blogId);
	}

	public void updateBlog(Blog blog) {
		sqlSession.update("BlogDAO.updateBlog", blog);
	}
}
