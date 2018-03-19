package com.cnfantasia.server.api.login.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private Log logger = LogFactory.getLog(getClass());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		String url = request.getRequestURL().toString();
		if(url != null) {
			if((url.contains("api.jiefangqu.com") || url.contains("api.linlile.cn")) && !url.contains("/API/login/")){
				url = url.replaceAll("http:", "https:");
			}
			
			url = url.replaceAll("login/doLogin.json", "security/doLoginSuccess.json");
			logger.debug("LoginSuccessHandler:" + url);
			response.sendRedirect(url);
		}
		
//		response.sendRedirect(url);
	}

}
