package test.spring.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

	private static final Logger logger = Logger.getLogger(TransactionService.class);
	
	@Transactional(readOnly=true,propagation=Propagation.MANDATORY)
	public void test1() {
		logger.info("test1");
	}
	
	@Transactional(readOnly=true,propagation=Propagation.MANDATORY)
	public void test2() {
		logger.info("test2");
	}
}
