package test.spring.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils extends org.springframework.web.util.WebUtils {
	
	public static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), cookie);
		}
		return cookieMap;
	}
	
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		Cookie oldCookie = getCookie(request, cookieName);
		if(oldCookie == null) {
			return;
		}
		
		Cookie newCookie = new Cookie(cookieName, null);
		newCookie.setPath("/");
		newCookie.setMaxAge(0);
		response.addCookie(newCookie);
	}
	
	
	public static void addCookie(HttpServletResponse response, String name, String value, String path) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		response.addCookie(cookie);
	}
}
