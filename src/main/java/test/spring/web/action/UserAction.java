package test.spring.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.common.ResponseUtils;
import test.spring.exception.BusinessException;
import test.spring.model.User;
import test.spring.model.UserForm;
import test.spring.service.UserService;
import test.spring.web.ResultCode;
import test.spring.web.WebUtils;

@Controller
@RequestMapping("/service/user")
public class UserAction extends AbstractAction {
	
	@Resource
	private UserService userService;

	@RequestMapping("getUser")
	public void getUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", defaultValue = "-1") int userId) throws BusinessException {
		checkNonPositiveParam(userId);
		
		User user = userService.getUser(userId);
		if(user != null) {
			ResponseUtils.writeSuccessResponse(request, response, user);
		} else {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.USER_NOT_EXISTS);
		}
	}
	
	@RequestMapping("register")
	public void register(HttpServletRequest request, HttpServletResponse response, UserForm userForm) {
		// verify input
		
		// verify Code
		
		// register user
		User user = userService.register(userForm);
		if(user != null) {
			ResponseUtils.writeSuccessResponse(request, response, user);
		} else {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.SERVER_ERROR);
		}
	}
	
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="loginname") String loginname,
			@RequestParam(value="password") String password,
			@RequestParam(value="verifyCode") String verifyCode) {
		// verify code
		
		User user = userService.getUserByName(loginname);
		if(user == null) {
			user = userService.getUserByEmail(loginname);
		}
		if(user == null) {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.USER_NOT_EXISTS);
			return;
		}
		
		if(!password.equals(user.getPassword())) {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.PASSWORD_NOT_CORRECT);
			return;
		}
		
		WebUtils.addCookie(response, "uid", user.getId()+"", "/");
		ResponseUtils.writeSuccessResponse(request, response, user);
	}
	
}
