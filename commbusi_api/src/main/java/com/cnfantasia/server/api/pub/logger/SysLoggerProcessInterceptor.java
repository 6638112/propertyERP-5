/**   
 * Filename:    SysLoggerProcessInterceptor.java   
 * @version:    1.0  
 * Create at:   2014年8月8日 上午6:15:46   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.pub.logger;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.commSysLogger.service.ISysLoggerManager;
import com.cnfantasia.server.business.pub.CommBaseController;
import com.cnfantasia.server.business.pub.logger.AbstractSysLoggerProcessInterceptor;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;

/**
 * Filename: SysLoggerProcessInterceptor.java
 * 
 * @version: 1.0.0 Create at: 2014年8月8日 上午6:15:46 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月8日 shiyl 1.0 1.0 Version
 */
public class SysLoggerProcessInterceptor extends AbstractSysLoggerProcessInterceptor {
	/** 日志对象 */
	// private static Log logger =
	// LogFactory.getLog(AbstractSysLoggerProcessInterceptor.class);

	public static final String REQUEST_KEY_STARTTIME = AbstractSysLoggerProcessInterceptor.class.getName() + "_StartTime";
	// public static final String REQUEST_KEY_ENDTTIME =
	// AbstractSysLoggerProcessInterceptor.class.getName()+"_EndTime";
	public static final String REQUEST_KEY_UQEXPKEY = CommBaseController.class.getName() + "_uniqueExpCode";
	public static final String REQUEST_KEY_LOGGER_ISDONE_FLAG = CommBaseController.class.getName() + "_isDone";

	private ISysLoggerManager<CommLogger> sysLoggerManager;

	public void setSysLoggerManager(ISysLoggerManager<CommLogger> sysLoggerManager) {
		this.sysLoggerManager = sysLoggerManager;
	}

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
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex01)
			throws Exception {
		sysLoggerManager.doLoggerRecord(request, response, handler, ex01);
	}

}
