package test.spring.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import test.spring.common.ResponseUtils;
import test.spring.exception.BusinessException;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	private static final Logger logger = Logger.getLogger(GlobalControllerAdvice.class);
	
	@ExceptionHandler(value = Exception.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logRequestParameters(request);
		logger.error("handleGlobalException:", e);
		// 参数类型不匹配、参数缺失
		if(e instanceof TypeMismatchException || e instanceof MissingServletRequestParameterException) {
			ResponseUtils.writeEmptyResponse(request, response, ResultCode.PARAMETER_ERROR);
		} else if(e instanceof BusinessException) {
			BusinessException be = (BusinessException) e;
			ResponseUtils.writeResponse(request, response, be.getCode(), be.getMsg(), null);
		} else {
			ResponseUtils.writeErrorResponse(request, response, null);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void logRequestParameters(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getRequestURI() + " parameters :[");
		Enumeration<String> names = request.getParameterNames();
		boolean exists = false;
		while(names.hasMoreElements()) {
			exists = true;
			
			String name = names.nextElement();
			String value = request.getParameter(name);
			sb.append(name);
			sb.append("=");
			sb.append(value);
			sb.append(",");
		}
		if(exists) {
			sb.setLength(sb.length() - 1);
		}
		sb.append("]");
		
		logger.error("handleGlobalException logArgs: " + sb.toString());
	}
}
