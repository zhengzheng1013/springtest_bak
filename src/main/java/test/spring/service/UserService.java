package test.spring.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import test.spring.dao.UserDAO;
import test.spring.model.User;
import test.spring.model.UserForm;

@Service
public class UserService {
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private AccountService accountService;
	
	public boolean addUser(UserForm userForm) {
		return userDAO.addUser(userForm);
	}
	
	public User getUser(int userId) {
		return userDAO.getUser(userId);
	}
	
	public User getUserByName(String username) {
		return userDAO.getUserByName(username);
	}
	
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}
	
	public User register(UserForm userForm) {
		String username = userForm.getUsername();
		String email = userForm.getEmail();
		
		User oldUser = getUserByName(username);
		if(oldUser == null) {
			oldUser = getUserByEmail(email);
		}
		
		if(oldUser != null) {
			logger.error("register duplicate: userForm=" + userForm + ", oldUser=" + oldUser);
			return null;
		}
		
		boolean result = addUser(userForm);
		if(!result) {
			return null;
		}
		
		User user = getUserByName(username);
		accountService.createAccount(user.getId());
		
		return user;
	}
}
