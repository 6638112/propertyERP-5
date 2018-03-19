package com.cnfantasia.server.ms.pub.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.cnfantasia.server.common.exception.FocException;
import com.cnfantasia.server.common.exception.FocRuntimeException;

/**
 * 描述:异常处理
 * 
 */
public class MyHandlerExceptionResolver extends SimpleMappingExceptionResolver {

	/** 日志对象 */
	private Log logger = LogFactory.getLog(MyHandlerExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, java.lang.Object o,
			java.lang.Exception e) {
		String errCode = "";
		String errMessage = "";
		if (e instanceof FocException) { // 类型是FocException
			FocException eobj = (FocException) e;
			errCode = eobj.getErrCode();
//			errMessage = eobj.getErrMessage();
			logger.error("errCode is: " + errCode);
			logger.error("errMessage is: " + errMessage);
			httpServletRequest.setAttribute("exceptionobj", eobj);
		} else if (e instanceof FocRuntimeException) { // 类型是FocRuntimeException
			FocRuntimeException eobj = (FocRuntimeException) e;
			errCode = eobj.getErrCode();
//			errMessage = eobj.getErrMessage();
			logger.error("errCode is: " + errCode);
			logger.error("errMessage is: " + errMessage);
			httpServletRequest.setAttribute("exceptionobj", eobj);
		} else {
			FocException eobj = new FocException((Throwable) e);
			errCode = eobj.getErrCode();
			logger.error("the " + errCode + " is defaultErrCode");
			httpServletRequest.setAttribute("exceptionobj", eobj);
		}
		logger.error("----error e:" + e.getMessage(), e);
		return super.doResolveException(httpServletRequest, httpServletResponse, o, e);
	}
}
