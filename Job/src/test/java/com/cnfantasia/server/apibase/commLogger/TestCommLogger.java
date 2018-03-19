/**   
 * Filename:    TestCommLogger.java   
 * @version:    1.0  
 * Create at:   2014年8月5日 上午10:59:45   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月5日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.apibase.commLogger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;
import com.cnfantasia.server.domainbase.commLogger.service.ICommLoggerBaseService;

/**
 * Filename: TestCommLogger.java
 * 
 * @version: 1.0.0 Create at: 2014年8月5日 上午10:59:45 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月5日 shiyl 1.0 1.0 Version
 */
public class TestCommLogger extends BaseTest {
	
//	@Test
	public void testInsertBatch() {
		ICommLoggerBaseService commLoggerBaseService = ctx.getBean("commLoggerBaseService", ICommLoggerBaseService.class);
		List<CommLogger> commLoggerList = new ArrayList<CommLogger>();
		{
			CommLogger logger = new CommLogger();
			logger.setId(new BigInteger("22"));
			StringBuffer sbf = new StringBuffer();
			for (int i = 0; i < 100; i++) {
				sbf.append("fwefwefwef");
			}
			logger.setActionUrlAll(sbf.toString());
			commLoggerList.add(logger);
		}
		{
			CommLogger logger = new CommLogger();
			logger.setId(new BigInteger("23"));
			// logger.setExcepStackInfo("LocalizedMessage:HeaderManager.getDeviceId.isEmpty;Message:HeaderManager.getDeviceId.isEmpty;StackTrace:com.cnfantasia.server.api.pub.header.HeaderManager.getDeviceId(HeaderManager.java:63);com.cnfantasia.server.api.prize.web.PrizeController.doPrize(PrizeController.java:66);sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method);sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39);sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25);java.lang.reflect.Method.invoke(Method.java:597);org.springframework.web.method.support.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:215);org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:132);org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104);org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:749);org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:689);org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:83);org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:938);org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:870);org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:961);org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:852);javax.servlet.http.HttpServlet.service(HttpServlet.java:617);org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:837);javax.servlet.http.HttpServlet.service(HttpServlet.java:723);org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290);org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206);org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88);org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:108);org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235);org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:330);org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:118);org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:84);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);com.cnfantasia.server.api.pub.springSecurity.SimpleFilterSecurityInterceptor.invoke(SimpleFilterSecurityInterceptor.java:56);com.cnfantasia.server.api.pub.springSecurity.SimpleFilterSecurityInterceptor.doFilter(SimpleFilterSecurityInterceptor.java:42);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:103);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:113);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:154);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:45);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.authentication.www.BasicAuthenticationFilter.doFilter(BasicAuthenticationFilter.java:150);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:199);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:110);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.session.ConcurrentSessionFilter.doFilter(ConcurrentSessionFilter.java:125);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:87);org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:192);org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:160);org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:344);org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:261);org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235);org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206);org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233);org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191);org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127);org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103);org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109);org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293);org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861);org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:606);org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:489);java.lang.Thread.run(Thread.java:662);");
			// logger.setExcepStackInfo("LocalizedMessage:HeaderManager.getDeviceId.isEmpty;Message:HeaderManager.getDeviceId.isEmpty;StackTrace:com.cnfantasia.server.api.pub.header.HeaderManager.getDeviceId(HeaderManager.java:63);com.cnfantasia.server.api.prize.web.PrizeController.doPrize(PrizeController.java:66);sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method);sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39);sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25);java.lang.reflect.Method.invoke(Method.java:597);org.springframework.web.method.support.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:215);org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:132);org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104);org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:749);org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:689);org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:83);org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:938);org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:870);org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:961);org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:852);javax.servlet.http.HttpServlet.service(HttpServlet.java:617);org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:837);javax.servlet.http.HttpServlet.service(HttpServlet.java:723);org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290);org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206);org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88);org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:108);org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235);org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:330);org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:118);org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:84);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);com.cnfantasia.server.api.pub.springSecurity.SimpleFilterSecurityInterceptor.invoke(SimpleFilterSecurityInterceptor.java:56);com.cnfantasia.server.api.pub.springSecurity.SimpleFilterSecurityInterceptor.doFilter(SimpleFilterSecurityInterceptor.java:42);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:103);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:113);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:154);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:45);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.authentication.www.BasicAuthenticationFilter.doFilter(BasicAuthenticationFilter.java:150);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:199);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:110);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.session.ConcurrentSessionFilter.doFilter(ConcurrentSessionFilter.java:125);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:87);org.springframework.security.web.FilterChainProxy99999VirtualFilterChain.doFilter(FilterChainProxy.java:342);org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:192);org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:160);org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:344);org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:261);org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235);org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206);org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233);org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191);org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127);org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103);org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109);org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293);org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:861);org.apache.coyote.http11.Http11Protocol99999Http11ConnectionHandler.process(Http11Protocol.java:606);org.apache.tomcat.util.net.JIoEndpoint99999Worker.run(JIoEndpoint.java:489);java.lang.Thread.run(Thread.java:662);");
			logger.setExcepStackInfo(filterDollarStr("aa$$ee"));
			System.out.println(logger.getExcepStackInfo().length());
			commLoggerList.add(logger);
		}
		commLoggerBaseService.insertCommLoggerBatch(commLoggerList);
	}
	public static void main(String[] args) {
		System.out.println(filterDollarStr("aa$$ee"));
		System.out.println("$f34$g$$fwef".replaceAll("//$", "//$//$"));
	}
	public static String filterDollarStr(String str) {
		String sReturn = "";
		if (!StringUtils.isEmpty(str)) {
			if (str.indexOf('$', 0) > -1) {
				while (str.length() > 0) {
					if (str.indexOf('$', 0) > -1) {
						sReturn += str.subSequence(0, str.indexOf('$', 0));
						sReturn += "\\$";
						str = str.substring(str.indexOf('$', 0) + 1, str.length());
					} else {
						sReturn += str;
						str = "";
					}
				}
			} else {
				sReturn = str;
			}
		}
		return sReturn;
	}
}
