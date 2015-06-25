package test.spring.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.common.ResponseUtils;
import test.spring.service.AccountService;
import test.spring.web.ResultCode;
import test.spring.web.WebUtils;

@Controller
@RequestMapping("service/account")
public class AccountAction {
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping("recharge")
	public void recharge(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ammount") int ammount) {
		int userId = WebUtils.getLoginUserId(request);
		if(userId <= 0) {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.USER_NOT_EXISTS);
			return;
		}
		
		boolean result = accountService.modifyAmmount(userId, ammount);
		if(result) {
			ResponseUtils.writeSuccessResponse(request, response, null);
		} else {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.USER_NOT_EXISTS);
		}
	}
	
	@RequestMapping("transfer")
	public void transfer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "uid") int userId,
			@RequestParam(value = "ammount") double ammount) {
		int fromUserId = WebUtils.getLoginUserId(request);
		int toUserId = userId;
		if(fromUserId <=0 || toUserId <= 0) {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.USER_NOT_EXISTS);
			return;
		}
		
		boolean result = accountService.transfer(fromUserId, toUserId, ammount);
		if(result) {
			ResponseUtils.writeSuccessResponse(request, response, null);
		} else {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.USER_NOT_EXISTS);
		}
	}
}
