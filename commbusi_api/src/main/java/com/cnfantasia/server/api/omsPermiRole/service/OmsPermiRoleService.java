package com.cnfantasia.server.api.omsPermiRole.service;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.omsPermiRole.dao.OmsPermiRoleDao;
import com.cnfantasia.server.api.omsPermiRole.entity.OmsPermiRoleEntity;
import com.cnfantasia.server.domainbase.omsPermiRole.service.OmsPermiRoleBaseService;
/**
 * 描述:() 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsPermiRoleService extends OmsPermiRoleBaseService implements IOmsPermiRoleService{
	private OmsPermiRoleDao omsPermiRoleDao;
	
	public OmsPermiRoleDao getOmsPermiRoleDao() {
		return omsPermiRoleDao;
	}

	public void setOmsPermiRoleDao(OmsPermiRoleDao omsPermiRoleDao) {
		this.omsPermiRoleDao = omsPermiRoleDao;
	}

	@Override
	public OmsPermiRoleEntity getOmsPermiRoleById(String roleCode) {
		return omsPermiRoleDao.getOmsPermiRoleById(roleCode);
	}
	
	
}
