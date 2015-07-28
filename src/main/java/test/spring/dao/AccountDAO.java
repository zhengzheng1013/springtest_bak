package test.spring.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO extends AbstractDAO {
	
	public boolean createAccount(long userId) {
		return sqlSession.insert("AccountDAO.createAccount", userId) > 0;
	}
	
	public double getBalance(long userId) {
		return sqlSession.selectOne("AccountDAO.getBalance", userId);
	}
	
	public boolean modifyAmmount(long userId, double ammount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("ammount", ammount);
		return sqlSession.update("AccountDAO.modifyAmmount", params) > 0;
	}
}
