package test.spring.dao;

import org.springframework.stereotype.Repository;

import test.spring.model.User;
import test.spring.model.UserForm;

@Repository
public class UserDAO extends AbstractDAO {
	
	public User getUser(long userId) {
		return sqlSession.selectOne("UserDAO.getUser", userId);
	}
	
	public User getUserByName(String username) {
		return sqlSession.selectOne("UserDAO.getUserByName", username);
	}
	
	public User getUserByEmail(String email) {
		return sqlSession.selectOne("UserDAO.getUserByEmail", email);
	}
	
	public boolean addUser(UserForm userForm) {
		return sqlSession.insert("UserDAO.addUser", userForm) > 0;
	}
}
