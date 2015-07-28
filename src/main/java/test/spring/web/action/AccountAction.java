package test.spring.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.common.ResponseUtils;
import test.spring.service.AccountService;
import test.spring.web.ErrorCode;
import test.spring.web.WebUtils;

@Controller
@RequestMapping("service/account")
public class AccountAction {
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping("recharge")
	public void recharge(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ammount") double ammount) {
		long userId = WebUtils.getLoginUserId(request);
		if(userId <= 0) {
			ResponseUtils.writeEmptyResponse(request, response, ErrorCode.USER_NOT_EXISTS);
			return;
		}
		
		boolean result = accountService.modifyAmmount(userId, ammount);
		if(result) {
			ResponseUtils.writeSuccessResponse(request, response, null);
		} else {
			ResponseUtils.writeEmptyResponse(request, response, ErrorCode.USER_NOT_EXISTS);
		}
	}
	
	@RequestMapping("transfer")
	public void transfer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "uid") long userId,
			@RequestParam(value = "ammount") double ammount) {
		long fromUserId = WebUtils.getLoginUserId(request);
		long toUserId = userId;
		if(fromUserId <=0 || toUserId <= 0) {
			ResponseUtils.writeEmptyResponse(request, response, ErrorCode.USER_NOT_EXISTS);
			return;
		}
		
		boolean result = accountService.transfer(fromUserId, toUserId, ammount);
		if(result) {
			ResponseUtils.writeSuccessResponse(request, response, null);
		} else {
			ResponseUtils.writeEmptyResponse(request, response, ErrorCode.USER_NOT_EXISTS);
		}
	}
}
