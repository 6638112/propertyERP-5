package com.cnfantasia.server.common.validation.resolver;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 类名：AbstractIocResolver  <br />
 *
 * 功能：基于ioc容器的bean解析器
 */
public class AbstractIocResolver implements ApplicationContextAware {
	
	// spring容器
	protected ApplicationContext ctx;
	
	/**
	 *
	 */
	public <T> T resolveBean(String name, Class<T> clazz) throws Exception {
		return ctx.getBean(name, clazz);
	}

	/**
	 */
	//@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

}
