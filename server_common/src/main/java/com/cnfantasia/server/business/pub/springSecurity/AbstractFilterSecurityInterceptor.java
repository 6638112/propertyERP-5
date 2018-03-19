package com.cnfantasia.server.business.pub.springSecurity;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

/**
 * 描述: 核心安全过滤器，负责处理HTTP资源的安全
 * 生成securityMetadataSource，相当于自定义的InvocationSecurityMetadataSourceService。
 * 该InvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中， 供Spring
 * Security使用，用于权限校验。
 * 
 */

public abstract class AbstractFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	/**
	 * Method that is actually called by the filter chain.
	 * 
	 * @param request the servlet request
	 * @param response the servlet response
	 * @param chain the filter chain
	 * @throws IOException if the filter chain fails
	 * @throws ServletException if the filter chain fails
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}
	
	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public void destroy() {
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}