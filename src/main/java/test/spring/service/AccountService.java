package test.spring.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.spring.dao.AccountDAO;

@Service
public class AccountService {

	private static final Logger logger = Logger.getLogger(AccountService.class);

	@Resource
	private AccountDAO accountDAO;
	
	@Resource
	private TransactionService transactionService;

	public AccountService() {
		logger.info("AccountService construct");
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, readOnly = true)
	public double getBalance(int userId) {
		transactionService.test1();
		transactionService.test2();
		return accountDAO.getBalance(userId);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public boolean createAccount(int userId) {
		return accountDAO.createAccount(userId);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public boolean modifyAmmount(int userId, int ammount) {
		return accountDAO.modifyAmmount(userId, ammount);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public boolean transfer(int fromUserId, int toUserId, double ammount) {
		double fromBalance = this.getBalance(fromUserId);
		if (fromBalance < ammount) {
			logger.error("transfer ERROR, fromBalance=" + fromBalance
					+ ",ammount=" + ammount);
			return false;
		}

		boolean result = false;
		result = accountDAO.modifyAmmount(fromUserId, -ammount);
		if (result) {
			result = accountDAO.modifyAmmount(toUserId, ammount);
		}

		return result;
	}
}
