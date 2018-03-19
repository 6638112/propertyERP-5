package com.cnfantasia.server.ms.pub.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionManager {
//public static final String USER_CONTEXT_SESSION_KEY = UserContext.class.getName() + "CONTEXT_USER";
	
	/**记录忘记密码第一步的验证码验证结果*/
	public static final String Forget_Password_Result = "Forget_Password_Result";
	/**
	 * 各个短信验证码的存储
	 * validateCodeMap：{验证码类型1：验证码取值1，验证码类型2：验证码取值2},例如{1:"1232",2:"6593",3:"0625"}
	 */
	public static final String SESSION_KEY_VALIDATE_CODE_MAP="validateCodeMap";
	
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
