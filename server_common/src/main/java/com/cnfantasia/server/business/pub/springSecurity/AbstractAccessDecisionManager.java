package com.cnfantasia.server.business.pub.springSecurity;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 描述:投票决议管理器，自定义的AccessDecisionManager。
 * 
 */

public abstract class AbstractAccessDecisionManager implements AccessDecisionManager {

	/** 日志对象 */
	private Log logger = LogFactory.getLog(AbstractAccessDecisionManager.class);

	/**
	 * 通过URL取得的权限列表进行循环，然后跟登录的用户所具有的权限进行比对， 若相同，则表明该用户具有访问该资源的权利， 如果访问被拒绝，实现将抛出
	 * 一个AccessDeniedException异常。
	 */
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				// ga is user's role.
				if (needRole.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		logger.warn("----no right!" + "the url is:" + object.toString());
		throw new AccessDeniedException("您没有权限访问！");
	}

	/**
	 * 在启动的时候被 * AbstractSecurityInterceptor调用，用来决定AccessDecisionManager
	 * 是否可以执行传递ConfigAttribute。
	 */
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}
