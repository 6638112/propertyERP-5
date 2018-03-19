/**   
 * Filename:    TomcatLoggerListener.java   
 * @version:    1.0  
 * Create at:   2014年8月8日 上午4:33:29   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.pub.logger;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cnfantasia.server.business.commSysLogger.service.ISysLoggerManager;
import com.cnfantasia.server.business.pub.logger.AbstractTomcatLoggerListener;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;

/**
 * Filename: TomcatLoggerListener.java
 * 
 * @version: 1.0.0 Create at: 2014年8月8日 上午4:33:29 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月8日 shiyl 1.0 1.0 Version
 */
public class TomcatLoggerListener extends AbstractTomcatLoggerListener {
	private Log logger = LogFactory.getLog(getClass());
	
	@Override
	public void sycnDbdataOnDestroyed(ServletContextEvent event) {
		logger.info("start to synch logrecord to database.");
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		@SuppressWarnings("unchecked")
		ISysLoggerManager<CommLogger> sysLoggerManager = ac.getBean(ISysLoggerManager.class);
		sysLoggerManager.synch2DatabaseCurrThread();
		logger.info("synch logrecord to finished.");

	}

}
