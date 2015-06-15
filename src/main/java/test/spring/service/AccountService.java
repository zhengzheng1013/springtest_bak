package test.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import test.spring.dao.AccountDAO;

@Service
public class AccountService {
	
	@Resource
	private AccountDAO accountDAO;
	
	public double getBalance(int userId) {
		return accountDAO.getBalance(userId);
	}
	
	public boolean createAccount(int userId) {
		return accountDAO.createAccount(userId);
	}
	
	public boolean modifyAmmount(int userId, int ammount) {
		return accountDAO.modifyAmmount(userId, ammount);
	}
	
	public boolean transfer(int fromUserId, int toUserId, double ammount) {
		boolean result = false;
		result = accountDAO.modifyAmmount(fromUserId, -ammount);
		if(result) {
			result = accountDAO.modifyAmmount(toUserId, ammount);
		}
		
		if(!result) {
			// TODO rollback
		}
		return result;
	}
}
