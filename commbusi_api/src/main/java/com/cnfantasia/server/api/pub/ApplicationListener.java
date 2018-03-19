package com.cnfantasia.server.api.pub;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

/**
 * <p>
 * 应用启动配置
 * <p>
 * <p>
 * </p>
 * 
 * @author yewj
 */
public class ApplicationListener implements ServletContextListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent context) {
		ServletContext servletContext = context.getServletContext();
		String resourcePath = CnfantasiaCommbusiUtil.getSysParaValue("resourcePath");
		String apiPath = CnfantasiaCommbusiUtil.getSysParaValue("LastApiBaseUrl");
		String laPath = CnfantasiaCommbusiUtil.getSysParaValue("laUrl");
		String managerPath = CnfantasiaCommbusiUtil.getSysParaValue("OosConnectInfo");
		if (resourcePath == null) {
			resourcePath = "http://127.0.0.1:8080/resource";
		}
		if(apiPath != null && apiPath.endsWith("/")) {
			apiPath = apiPath.substring(0, apiPath.length() - 1);
		}
		if(managerPath != null && managerPath.endsWith("/")) {
			managerPath = managerPath.substring(0, managerPath.length() - 1);
		}
		servletContext.setAttribute("resourcePath", resourcePath);
		servletContext.setAttribute("apiPath", apiPath);
		servletContext.setAttribute("laPath", laPath);
		servletContext.setAttribute("managerPath", managerPath);
		
		servletContext.setAttribute("resourcePathHttps", resourcePath.replace("http:", "https:"));
		servletContext.setAttribute("apiPathHttps", apiPath.replace("http:", "https:"));
		servletContext.setAttribute("laPathHttps", laPath.replace("http:", "https:"));
		servletContext.setAttribute("managerPathHttps", managerPath.replace("http:", "https:"));
		
		servletContext.setAttribute("resourcePathNoHttp", resourcePath.replace("http:", ""));
		servletContext.setAttribute("apiPathNoHttp", apiPath.replace("http:", ""));
		servletContext.setAttribute("laPathNoHttp", laPath.replace("http:", ""));
		servletContext.setAttribute("managerPathNoHttp", managerPath.replace("http:", ""));
	}
}
