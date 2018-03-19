package com.cnfantasia.server.ms.omsPermiRole.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;

/**
 * 角色
 * 
 * @author wenfq
 * 
 */
public class OmsPermiRoleEntity extends OmsPermiRole {

	private static final long serialVersionUID = -4622178544018507971L;

	/**
	 * 角色所拥有的权限
	 */
	private List<OmsPermiFunction> omsPermiFunctionList;

	public List<OmsPermiFunction> getOmsPermiFunctionList() {
		return omsPermiFunctionList;
	}

	public void setOmsPermiFunctionList(List<OmsPermiFunction> omsPermiFunctionList) {
		this.omsPermiFunctionList = omsPermiFunctionList;
	}
}
