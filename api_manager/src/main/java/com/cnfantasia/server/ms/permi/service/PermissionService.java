package com.cnfantasia.server.ms.permi.service;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.ms.permi.dao.PermiDao;

/**
 * 运营后台权限服务类
 * 
 * @author wenfq  2017年1月17日
 *
 */
public class PermissionService {
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	PermiDao permiDao;

	public List<OmsPermiRole> getOmsPermiRoleByUserId(BigInteger userId) {
		return permiDao.getOmsPermiRoleByUserId(userId);
	}	
}
