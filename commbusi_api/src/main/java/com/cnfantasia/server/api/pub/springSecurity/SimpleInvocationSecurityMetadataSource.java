package com.cnfantasia.server.api.pub.springSecurity;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.service.IResourceService;
import com.cnfantasia.server.business.pub.springSecurity.AbstractInvocationSecurityMetadataSource;

/**
 * 描述: 在初始化时，从数据库提取权限和资源，装配到HashMap中。
 * 
 * 开放loadResourceDefine()方法，供增加角色、分配角色权限程序调用
 */

@Service
public class SimpleInvocationSecurityMetadataSource extends AbstractInvocationSecurityMetadataSource {
	/** 日志对象 */
	private static Log logger = LogFactory.getLog(SimpleInvocationSecurityMetadataSource.class);
	protected IResourceService resourceService;
	protected Map<String, Collection<ConfigAttribute>> resourcesMap = null;

	/**
	 * 注入数据操作对象
	 * 
	 * @param resourcesDao
	 */
	public SimpleInvocationSecurityMetadataSource(IResourceService resourceService) {
		this.resourceService = resourceService;
		loadResourceDefine();
	}

	/**
	 * 读取功能资源与权限的对应关系
	 */
	public void loadResourceDefine() {
		resourcesMap = resourceService.getAllSourceAndRole();
		logger.info("----Security：Read DB RoleResources ok.");
	}

	@Override
	public Map<String, Collection<ConfigAttribute>> getResourcesMap() {
		return resourcesMap;
	}
}