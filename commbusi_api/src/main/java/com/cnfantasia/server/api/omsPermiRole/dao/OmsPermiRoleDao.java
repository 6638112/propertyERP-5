package com.cnfantasia.server.api.omsPermiRole.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.omsPermiRole.entity.OmsPermiRoleEntity;
import com.cnfantasia.server.domainbase.omsPermiRole.dao.OmsPermiRoleBaseDao;
/**
 * 描述:() 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsPermiRoleDao extends OmsPermiRoleBaseDao implements IOmsPermiRoleDao{

	@Override
	public OmsPermiRoleEntity getOmsPermiRoleById(String roleCode) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		return sqlSession.selectOne("omsPermiRole.select_omsPermiRole_byCode", paramMap);
	}
	
	
}
