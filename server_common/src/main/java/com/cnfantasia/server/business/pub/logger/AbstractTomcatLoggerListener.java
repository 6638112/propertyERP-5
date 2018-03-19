/**   
 * Filename:    AbstractTomcatLoggerListener.java   
 * @version:    1.0  
 * Create at:   2014年8月6日 上午5:00:12   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月6日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.business.pub.logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Filename: AbstractTomcatLoggerListener.java
 * 
 * @version: 1.0.0 Create at: 2014年8月6日 上午5:00:12 
 * Description:tomcat 关闭时，同步缓存的日志到数据库
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月6日 shiyl 1.0 1.0 Version
 */
public abstract class AbstractTomcatLoggerListener implements ServletContextListener {
	private Log logger = LogFactory.getLog(getClass());
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info(AbstractTomcatLoggerListener.class+" is contextInitialized.");
	} 
	
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info(AbstractTomcatLoggerListener.class+" is contextDestroyed method is called.");
		sycnDbdataOnDestroyed(event);
	}
	
	public abstract void sycnDbdataOnDestroyed(ServletContextEvent event);
	
}
