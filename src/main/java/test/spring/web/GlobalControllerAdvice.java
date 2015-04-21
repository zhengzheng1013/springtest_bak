package test.spring.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import test.spring.common.ResponseUtils;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	private static final Logger logger = Logger.getLogger(GlobalControllerAdvice.class);
	
	@ExceptionHandler(value = Exception.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logRequestParameters(request);
		logger.error("handleGlobalException:", e);
		
		ResponseUtils.writeErrorResponse(request, response, null);
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
