package com.cnfantasia.server.api.pub.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionManager {
//public static final String USER_CONTEXT_SESSION_KEY = UserContext.class.getName() + "CONTEXT_USER";
	
	
	
	public static HttpSession getSession(){
		HttpServletRequest request = getRequest();
		if(request==null){return null;}
		return request.getSession();
	}
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(servletRequestAttributes==null){return null;}
		HttpServletRequest request =servletRequestAttributes.getRequest(); 
		return request;
	}
}
