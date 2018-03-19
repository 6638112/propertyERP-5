package com.cnfantasia.server.api.pub.springSecurity;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.cnfantasia.server.business.pub.springSecurity.AbstractFilterSecurityInterceptor;

/**
 * 描述: 核心安全过滤器，负责处理HTTP资源的安全
 * 生成securityMetadataSource，相当于自定义的InvocationSecurityMetadataSourceService。
 * 该InvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中， 供Spring
 * Security使用，用于权限校验。
 * 
 */

public class SimpleFilterSecurityInterceptor extends AbstractFilterSecurityInterceptor {
	protected FilterInvocationSecurityMetadataSource securityMetadataSource;
	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource) {
		this.securityMetadataSource = newSource;
	}
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
	
}