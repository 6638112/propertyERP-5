package com.cnfantasia.server.ms.pub.timeout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *	校验拦截器
 */
public class TimeoutInterceptor extends HandlerInterceptorAdapter {
	// 日志记录器
	private static final Log logger = LogFactory.getLog(TimeoutInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug(getClass() + "---preHandle");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
		logger.debug(getClass() + "---afterCompletion");
	}


}