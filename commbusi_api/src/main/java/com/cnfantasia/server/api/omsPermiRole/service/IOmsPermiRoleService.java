package com.cnfantasia.server.api.omsPermiRole.service;

import com.cnfantasia.server.api.omsPermiRole.entity.OmsPermiRoleEntity;
import com.cnfantasia.server.domainbase.omsPermiRole.service.IOmsPermiRoleBaseService;
/**
 * 描述:() 具体业务Service层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiRoleService extends IOmsPermiRoleBaseService {
	
	/**
	 * 根据角色编码查询角色详情
	 * @param roleCode
	 * @return
	 */
	public OmsPermiRoleEntity getOmsPermiRoleById(String roleCode);
	
	
	
}
