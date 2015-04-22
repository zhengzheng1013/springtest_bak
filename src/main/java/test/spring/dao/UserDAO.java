package test.spring.dao;

import org.springframework.stereotype.Repository;

import test.spring.model.User;

@Repository
public class UserDAO extends AbstractDAO {
	
	public User getUser(int userId) {
		return sqlSession.selectOne("UserDAO.getUser", userId);
	}
}
