package com.cnfantasia.server.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FocContext implements ApplicationContextAware {
	/** 容器对象 */
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		FocContext.applicationContext = applicationContext;
//		String[] beanNames = applicationContext.getBeanDefinitionNames();
//		for(String name:beanNames){
//			System.out.println(name);
//		}
	}

	public static ApplicationContext getApplicationContext() {
		//ContextLoader.getCurrentWebApplicationContext();//Spring 3.0以后，提供了一个静态方法，可以直接获取到web环境下的ApplicationContext 
		return applicationContext;
	}
	
}
