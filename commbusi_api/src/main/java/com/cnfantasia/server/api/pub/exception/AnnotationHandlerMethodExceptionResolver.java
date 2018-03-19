/**   
* Filename:    AnnotationHandlerMethodExceptionResolver.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午6:23:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnfantasia.server.business.commSysLogger.service.ISysLoggerManager;
import com.cnfantasia.server.business.pub.exception.AbstractAnnotationHandlerMethodExceptionResolver;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;

/**
 * Filename:    AnnotationHandlerMethodExceptionResolver.java
 * @version:    1.0.0
 * Create at:   2014年8月8日 上午6:23:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月8日       shiyl             1.0             1.0 Version
 */
public class AnnotationHandlerMethodExceptionResolver extends AbstractAnnotationHandlerMethodExceptionResolver{
	private ISysLoggerManager<CommLogger> sysLoggerManager;
	public void setSysLoggerManager(ISysLoggerManager<CommLogger> sysLoggerManager) {
		this.sysLoggerManager = sysLoggerManager;
	}
	
	@Override
	public void resolveExceptionPre(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		// 将异常信息存入到request中，便于后面记录日志时获取
		request.setAttribute(ExceptionConstant.REQUEST_KEY_EXCEPTION, exception);
		
	}
	
	@Override
	public void resolveExceptionEnd(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		//日志处理
		sysLoggerManager.doLoggerRecord(request, response, handler, exception);//写在最后
	}
	
}
