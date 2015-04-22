package test.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import test.spring.dao.UserDAO;
import test.spring.model.User;

@Service
public class UserService {
	
	@Resource
	private UserDAO userDAO;
	
	public User getUser(int userId) {
		return userDAO.getUser(userId);
	}
}
