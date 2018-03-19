package com.cnfantasia.server.ms.logger;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.business.pub.CommBaseController;
import com.cnfantasia.server.business.pub.logger.AbstractSysLoggerProcessInterceptor;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;
import com.cnfantasia.server.ms.logger.service.ISysLoggermsManager;

public class SysLoggerManagerInterceptor extends AbstractSysLoggerProcessInterceptor {
	
	public static final String REQUEST_KEY_STARTTIME = AbstractSysLoggerProcessInterceptor.class.getName() + "_StartTime";
	public static final String REQUEST_KEY_UQEXPKEY = CommBaseController.class.getName() + "_uniqueExpCode";
	public static final String REQUEST_KEY_LOGGER_ISDONE_FLAG = CommBaseController.class.getName() + "_isDone";
	@Resource
	private ISysLoggermsManager<CommLogger> sysLoggerManager;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		{// 请求时间
			request.setAttribute(REQUEST_KEY_STARTTIME, new Date());
		}
		{// 调试信息
			StringBuffer uniqueCode = new StringBuffer();
			uniqueCode.append(new Date().getTime());
			uniqueCode.append("R");
			uniqueCode.append(RandomUtils.createRandom(true, 5));
			if (UserContext.getOperId() != null) {
				uniqueCode.append("U");
				uniqueCode.append(UserContext.getOperId());
			}
			request.setAttribute(REQUEST_KEY_UQEXPKEY, uniqueCode.toString());// 存入request
		}
		{// 是否已经记录日志的标识
			request.setAttribute(REQUEST_KEY_LOGGER_ISDONE_FLAG, false);
		}
		return super.preHandle(request, response, handler);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex01) throws Exception {
		String reqUrl = request.getRequestURI();
		if(reqUrl.equals("/api_manager/security/toIndex.html")  
				|| reqUrl.equals("/api_manager/security/loginPage2.html")  || reqUrl.equals("/api_manager/security/doLoginSuccess.html")
				|| reqUrl.equals("/api_manager/security/accessNoPermission.html")){
		}else{
			sysLoggerManager.doLoggerRecord(request, response, handler, ex01);
		}
	}

}
