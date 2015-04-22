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
import test.spring.service.UserService;
import test.spring.web.ResultCode;

@Controller
@RequestMapping("/user")
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
}
